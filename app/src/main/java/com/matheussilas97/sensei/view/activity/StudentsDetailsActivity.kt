package com.matheussilas97.sensei.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.databinding.ActivityStudentsDetailsBinding
import com.matheussilas97.sensei.databinding.DialogPresenceNumberBinding
import com.matheussilas97.sensei.util.BaseActvity
import com.matheussilas97.sensei.util.Constants
import com.matheussilas97.sensei.viewmodel.StudentsViewModel
import kotlin.properties.Delegates


class StudentsDetailsActivity : BaseActvity() {

    private lateinit var binding: ActivityStudentsDetailsBinding
    private lateinit var viewModel: StudentsViewModel

    private var idStudent by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[StudentsViewModel::class.java]

        binding.toolbar.inflateMenu(R.menu.menu_edit)

        onClick()
        setInfoFromStudent()
    }

    private fun onClick() {
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

            val student = viewModel.loadStudent(idStudent)
            var gender = ""
                if (student.gender == Constants.MALE){
                    gender = getString(R.string.Male)
                }else {
                    gender = getString(R.string.Female)
                }

            binding.nameStudent.text = student.name
            binding.dateStart.text =
                "$dateStartDetails ${student.dateStart}"
            binding.grade.text = student.grade

            try {
                binding.age.text = "$ageDetails ${viewModel.calculateAge(birthDate)}"
            }catch (e: Exception){
                binding.age.visibility = View.GONE
            }

            binding.gender.text = "${getString(R.string.gender)}: $gender"
            binding.phone.text = student.phone
            binding.presence.text = student.presence.toString()
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
            toast(getString( R.string.amount_change_sucess))
            dialog.dismiss()
        }

    }

    private fun callPhone() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${binding.phone.text}")
        startActivity(intent)
    }
}