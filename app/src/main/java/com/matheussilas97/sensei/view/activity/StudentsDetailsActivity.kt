package com.matheussilas97.sensei.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.databinding.ActivityStudentsDetailsBinding
import com.matheussilas97.sensei.databinding.DialogDeleteBinding
import com.matheussilas97.sensei.databinding.DialogPresenceNumberBinding
import com.matheussilas97.sensei.util.Constants
import com.matheussilas97.sensei.viewmodel.StudentsViewModel
import kotlin.properties.Delegates


class StudentsDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentsDetailsBinding

    private lateinit var viewModel: StudentsViewModel

    private var idStudent by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[StudentsViewModel::class.java]

        binding.toolbar.inflateMenu(R.menu.menu_edit)

        binding.toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener menuToolbar(it)
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.iconPhone.setOnClickListener {
            callPhone()
        }

        binding.editPresence.setOnClickListener {
            editPresenceNumber()
        }

        setInfoFromStudent()
    }

    override fun onResume() {
        super.onResume()
        setInfoFromStudent()
    }

    private fun menuToolbar(it: MenuItem): Boolean {
        when (it.itemId) {
            R.id.ic_edit -> {
                val intent = Intent(this, StudentEditActivity::class.java)
                intent.putExtra(Constants.STUDENT_ID, idStudent)
                startActivity(intent)
                return true
            }
            else -> {
                return false
            }
        }
    }

    private fun setInfoFromStudent() {
        val extras = intent.extras
        if (extras != null) {
            idStudent = extras.getInt(Constants.STUDENT_ID)

            val dateStartDetails = getString(R.string.start_date_details)
            val ageDetails = getString(R.string.age_details)

            val birthDate = viewModel.loadStudent(idStudent).birthDate

            binding.nameStudent.text = viewModel.loadStudent(idStudent).name
            binding.dateStart.text =
                "$dateStartDetails ${viewModel.loadStudent(idStudent).dateStart}"
            binding.grade.text = viewModel.loadStudent(idStudent).grade
            binding.age.text = "$ageDetails ${viewModel.calculateAge(birthDate)}"
            binding.phone.text = viewModel.loadStudent(idStudent).phone
            binding.presence.text = viewModel.loadStudent(idStudent).presence.toString()
        }
    }

    private fun editPresenceNumber() {

        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        val binding: DialogPresenceNumberBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this), R.layout.dialog_presence_number, null, false
        )
        binding.editAmountPresence.setText(viewModel.loadStudent(idStudent).presence.toString())
        builder.setView(binding.root)
        val dialog = builder.show()
        dialog.setCancelable(true)
        binding.btnClose.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnChage.setOnClickListener {
            viewModel.updatePresenceAmount(
                binding.editAmountPresence.text.toString().toInt(),
                idStudent
            )
            setInfoFromStudent()
            Toast.makeText(this, R.string.amount_change_sucess, Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

    }

    private fun callPhone() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${binding.phone.text}")
        startActivity(intent)
    }
}