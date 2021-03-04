package com.matheussilas97.sensei.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.databinding.ActivityStudentsDetailsBinding
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

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.iconPhone.setOnClickListener {
            callPhone()
        }

        setInfoFromStudent()
    }

    override fun onResume() {
        super.onResume()
        setInfoFromStudent()
    }

    private fun setInfoFromStudent() {
        val extras = intent.extras
        if (extras != null) {
            idStudent = extras.getInt(Constants.STUDENT_ID)

            val dateStartDetails = getString(R.string.start_date_details)
            val ageDetails = getString(R.string.age_details)

            binding.nameStudent.text = viewModel.loadStudent(idStudent).name
            binding.dateStart.text = "$dateStartDetails ${viewModel.loadStudent(idStudent).dateStart}"
            binding.grade.text = viewModel.loadStudent(idStudent).grade
            binding.age.text = "$ageDetails ${viewModel.loadStudent(idStudent).age}"
            binding.phone.text = viewModel.loadStudent(idStudent).phone
            binding.presence.text = viewModel.loadStudent(idStudent).presence.toString()
        }
    }

    private fun callPhone(){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${binding.phone.text}")
        startActivity(intent)
    }
}