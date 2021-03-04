package com.matheussilas97.sensei.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.adapter.StudentsAdapter
import com.matheussilas97.sensei.databinding.ActivityStudentBinding
import com.matheussilas97.sensei.databinding.DialogDeleteBinding
import com.matheussilas97.sensei.util.Constants
import com.matheussilas97.sensei.viewmodel.StudentsViewModel
import kotlin.properties.Delegates

class StudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentBinding

    private lateinit var viewModel: StudentsViewModel

    private var idGroup by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[StudentsViewModel::class.java]

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, StudentsAddActivity::class.java)
            intent.putExtra(Constants.GROUP_ID, idGroup)
            startActivity(intent)
        }

        takeValuesFromId()
        buildStudentList()
    }

    override fun onResume() {
        super.onResume()
        viewModel.listFromGroup(idGroup)
        viewModel.countStudentsFromGroups(idGroup)
        binding.countStudents.text = viewModel.countStudentsFromGroups(idGroup).toString()
    }

    private fun takeValuesFromId() {
        val extras = intent.extras
        if (extras != null) {
            val groupName = extras.getString(Constants.GROUP_NAME)
            idGroup = extras.getInt(Constants.GROUP_ID)
            binding.titlePage.text = groupName

            binding.countStudents.text = viewModel.countStudentsFromGroups(idGroup).toString()

            viewModel.listFromGroup(idGroup)
        }
    }

    private fun buildStudentList() {
        viewModel.studentListFromGroup.observe(this, Observer { data ->
            if (!data.isNullOrEmpty()) {
                binding.textInfo.visibility = View.GONE
                binding.recyclerStudents.visibility = View.VISIBLE

                val adapter = StudentsAdapter(this, data)
                binding.recyclerStudents.layoutManager = LinearLayoutManager(this)
                binding.recyclerStudents.adapter = adapter
                adapter.addOnItemClickListener(object : StudentsAdapter.OnItemClickListener {
                    override fun onClick(id: Int) {
                        val intent = Intent(this@StudentActivity, StudentsDetailsActivity::class.java)
                        intent.putExtra(Constants.STUDENT_ID, id)
                        startActivity(intent)
                    }

                    override fun onDelete(id: Int) {
                        deleteStudent(id)
                    }
                })
            } else {
                binding.textInfo.visibility = View.VISIBLE
                binding.recyclerStudents.visibility = View.GONE
            }
        })
    }

    private fun deleteStudent(idStudent: Int) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        val binding: DialogDeleteBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this), R.layout.dialog_delete, null, false
        )
        binding.txtDelete.setText(R.string.delete_student_message)

        builder.setView(binding.root)
        val dialog = builder.show()
        dialog.setCancelable(true)
        binding.btnClose.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnDelete.setOnClickListener {
            viewModel.deleteStudent(idStudent)
            viewModel.countStudentsFromGroups(idGroup)
            viewModel.listFromGroup(idGroup)
            dialog.dismiss()
        }
    }
}