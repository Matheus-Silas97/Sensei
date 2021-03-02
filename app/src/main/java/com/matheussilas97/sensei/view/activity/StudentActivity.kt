package com.matheussilas97.sensei.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.adapter.StudentsAdapter
import com.matheussilas97.sensei.databinding.ActivityStudentBinding
import com.matheussilas97.sensei.util.Constants

class StudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val groupName = extras.getString(Constants.GROUP_NAME)
            binding.toolbar.title = groupName
        }

        binding.toolbar.setOnClickListener {
            onBackPressed()
        }

    }

    private fun buildStudentList(){
//        val adapter = StudentsAdapter(this, )
//        binding.recyclerStudents.layoutManager = LinearLayoutManager(this)
//        binding.recyclerStudents.adapter = adapter

    }
}