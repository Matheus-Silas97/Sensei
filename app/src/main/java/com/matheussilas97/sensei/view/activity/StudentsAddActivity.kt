package com.matheussilas97.sensei.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.database.model.StudentsModel
import com.matheussilas97.sensei.databinding.ActivityStudentsAddBinding
import com.matheussilas97.sensei.util.Constants
import com.matheussilas97.sensei.util.MaskEditUtil
import com.matheussilas97.sensei.viewmodel.StudentsViewModel
import kotlin.properties.Delegates

class StudentsAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentsAddBinding

    private lateinit var viewModel: StudentsViewModel

    private var idGroup by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[StudentsViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            idGroup = extras.getInt(Constants.GROUP_ID)
            binding.btnAdd.setOnClickListener {
                registerStudent(idGroup)
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.editPhone.addTextChangedListener(MaskEditUtil.mask(binding.editPhone, "(##) #####-####"))
        binding.editStartDate.addTextChangedListener(MaskEditUtil.mask(binding.editStartDate, "##/##/####"))
    }

    override fun onResume() {
        super.onResume()

        viewModel.listFromGroup(idGroup)
    }

    private fun registerStudent(idGroup: Int) {
        val name = binding.editStudentName.text.toString()
        val age = binding.editAge.text.toString()
        val grade = binding.editGrade.text.toString()
        val phone = binding.editPhone.text.toString()
        val dateStart = binding.editStartDate.text.toString()

        if (name.isEmpty()) {
            toast(getString(R.string.empty_name))
        } else if (age.isEmpty()) {
            toast(getString(R.string.empty_age))
        } else if (grade.isEmpty()) {
            toast(getString(R.string.empty_grade))
        } else if (phone.isEmpty()) {
            toast(getString(R.string.empty_phone))
        } else if (dateStart.isEmpty()) {
            toast(getString(R.string.empty_date_start))
        } else {
            val model = StudentsModel(0, name, grade, age.toInt(), phone, 0, dateStart, idGroup)
            viewModel.saveStudent(model)
            onBackPressed()
        }

    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}