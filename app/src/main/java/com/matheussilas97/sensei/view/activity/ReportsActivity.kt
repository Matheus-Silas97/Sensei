package com.matheussilas97.sensei.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.databinding.ActivityReportsBinding
import com.matheussilas97.sensei.viewmodel.GroupsViewModel

class ReportsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportsBinding

    private lateinit var viewModel: GroupsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener{
            onBackPressed()
        }

        viewModel = ViewModelProvider(this)[GroupsViewModel::class.java]

        binding.totalGroups.text = viewModel.totalGroups().toString()
        binding.totalStudents.text = viewModel.totalStudents().toString()


    }
}