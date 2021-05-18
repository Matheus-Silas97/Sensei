package com.matheussilas97.sensei.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.database.model.StudentsModel
import com.matheussilas97.sensei.databinding.ActivityStudentsAddBinding
import com.matheussilas97.sensei.util.BaseActvity
import com.matheussilas97.sensei.util.Constants
import com.matheussilas97.sensei.util.MaskEditUtil
import com.matheussilas97.sensei.viewmodel.StudentsViewModel
import kotlin.properties.Delegates

class StudentsAddActivity : BaseActvity() {

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

        onClick()
        masks()
        observer()

    }

    private fun onClick() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
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

        val model = StudentsModel(0, name, grade, birthDate, phone, 0, dateStart, idGroup, gender)
        if (!viewModel.validateAddStudent(model, this)) {
          viewModel.addStudentsStatus.observe(this, Observer {
              toast(it)
          })
        } else {
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

}