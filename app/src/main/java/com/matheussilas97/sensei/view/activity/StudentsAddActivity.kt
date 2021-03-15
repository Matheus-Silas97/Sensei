package com.matheussilas97.sensei.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
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
        binding.editBirthDate.addTextChangedListener(MaskEditUtil.mask(binding.editBirthDate, "##/##/####"))

          observer()

    }

    override fun onResume() {
        super.onResume()

        viewModel.listFromGroup(idGroup)
    }

    private fun registerStudent(idGroup: Int) {
        val name = binding.editStudentName.text.toString()
        var gender = ""
        val birthDate = binding.editBirthDate.text.toString()
        val grade = binding.editGrade.text.toString()
        val phone = binding.editPhone.text.toString()
        val dateStart = binding.editStartDate.text.toString()

            if (binding.radioMale.isChecked){
                gender = Constants.MALE
            }else if (binding.radioFemale.isChecked){
                gender = Constants.FEMALE
            }

        if (name.isEmpty()) {
            toast(getString(R.string.empty_name))
        }else if (gender.isNullOrEmpty()){
            toast(getString(R.string.empty_gender))
        } else if (birthDate.isEmpty()) {
            toast(getString(R.string.empty_birthday))
        } else if (grade.isEmpty()) {
            toast(getString(R.string.empty_grade))
        } else if (phone.isEmpty()) {
            toast(getString(R.string.empty_phone))
        } else if (dateStart.isEmpty()) {
            toast(getString(R.string.empty_date_start))
        } else {
            val model = StudentsModel(0, name, grade, birthDate, phone, 0, dateStart, idGroup, gender)
            viewModel.saveStudent(model)
        }

    }

    private fun observer(){
        viewModel.saveStudent.observe(this, Observer {
            if (it){
                toast(getString(R.string.add_student_sucess))
                onBackPressed()
            }else{
                toast(getString(R.string.add_student_fail))
            }
        })
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}