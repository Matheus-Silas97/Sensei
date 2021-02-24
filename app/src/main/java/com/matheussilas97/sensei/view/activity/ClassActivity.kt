package com.matheussilas97.sensei.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.adapter.ClassAdapter
import com.matheussilas97.sensei.databinding.ActivityClassBinding

class ClassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding = ActivityClassBinding.inflate(layoutInflater)


    }

    private fun listClass() {
        //val adapterClass = ClassAdapter()
        binding.recyclerClass.layoutManager = LinearLayoutManager(this)
        //binding.recyclerClass.adapter = adapterClass

    }
}