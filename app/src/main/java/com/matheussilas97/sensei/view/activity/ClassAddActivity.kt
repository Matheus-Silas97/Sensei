package com.matheussilas97.sensei.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.databinding.ActivityClassAddBinding
import com.matheussilas97.sensei.viewmodel.ClassViewModel

class ClassAddActivity : AppCompatActivity() {

    private lateinit var viewModel: ClassViewModel

    private lateinit var binding: ActivityClassAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityClassAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ClassViewModel::class.java]

        binding.toolbar.setOnClickListener {
            onBackPressed()
        }

        binding.btnAdd.setOnClickListener {

        }
    }

}