package com.matheussilas97.sensei.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.database.model.StudentsModel
import com.matheussilas97.sensei.databinding.ActivityStudentEditBinding
import com.matheussilas97.sensei.util.BaseActvity
import com.matheussilas97.sensei.util.Constants
import com.matheussilas97.sensei.util.MaskEditUtil
import com.matheussilas97.sensei.viewmodel.StudentsViewModel
import kotlin.properties.Delegates

class StudentEditActivity : BaseActvity() {

    private lateinit var binding: ActivityStudentEditBinding
    private lateinit var viewModel: StudentsViewModel

    private var idStudent by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[StudentsViewModel::class.java]

        onClick()
        setInfoFromStudent()
        observer()
        masks()

    }

    private fun masks() {
        binding.editPhone.addTextChangedListener(
            MaskEditUtil.mask(
                binding.editPhone,
                "(##) #####-####"
            )
        )
        binding.editStartDate.addTextChangedListener(
            MaskEditUtil.mask(
                binding.editStartDate,
                "##/##/####"
            )
        )
        binding.editBirthDate.addTextChangedListener(
            MaskEditUtil.mask(
                binding.editBirthDate,
                "##/##/####"
            )
        )
    }

    private fun onClick() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.btnSave.setOnClickListener {
            editStudent(viewModel.loadStudent(idStudent).classId)
        }
    }

    private fun setInfoFromStudent() {
        val extras = intent.extras
        if (extras != null) {
            idStudent = extras.getInt(Constants.STUDENT_ID)

            val gender = viewModel.loadStudent(idStudent).gender
            if (gender == Constants.MALE) {
                binding.radioMale.isChecked = true
            } else {
                binding.radioFemale.isChecked = true
            }

            binding.editStudentName.setText(viewModel.loadStudent(idStudent).name)
            binding.editStartDate.setText(viewModel.loadStudent(idStudent).dateStart)
            binding.editGrade.setText(viewModel.loadStudent(idStudent).grade)
            binding.editBirthDate.setText(viewModel.loadStudent(idStudent).birthDate)
            binding.editPhone.setText(viewModel.loadStudent(idStudent).phone)

        }
    }

    private fun editStudent(idGroup: Int) {
        val name = binding.editStudentName.text.toString()
        var gender = ""
        val birthDate = binding.editBirthDate.text.toString()
        val grade = binding.editGrade.text.toString()
        val phone = binding.editPhone.text.toString()
        val dateStart = binding.editStartDate.text.toString()

        if (binding.radioMale.isChecked) {
            gender = Constants.MALE
        } else if (binding.radioFemale.isChecked) {
            gender = Constants.FEMALE
        }

        val model = StudentsModel(
            idStudent,
            name,
            grade,
            birthDate,
            phone,
            viewModel.loadStudent(idStudent).presence,
            dateStart,
            idGroup, gender
        )

        if (!viewModel.validateAddStudent(model, this)) {
            viewModel.addStudentsStatus.observe(this, Observer {
                toast(it)
            })
        } else {
            viewModel.saveStudent(model)
            onBackPressed()
        }
    }

    private fun observer() {
        viewModel.saveStudent.observe(this, Observer {
            if (it) {
                toast(getString(R.string.update_data_sucess))
                onBackPressed()
            } else {
                toast(getString(R.string.error_message))
            }
        })

    }

}