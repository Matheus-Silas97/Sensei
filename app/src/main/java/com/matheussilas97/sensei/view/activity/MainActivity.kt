package com.matheussilas97.sensei.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.btnChamada.setOnClickListener {
            startActivity(Intent(this, ChamadaActivity::class.java))
        }

        binding.btnStudents.setOnClickListener {
            startActivity(Intent(this, StudentActivity::class.java))
        }

        binding.btnClass.setOnClickListener {
            startActivity(Intent(this, ClassActivity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }


    }
}