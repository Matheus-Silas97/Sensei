package com.matheussilas97.sensei.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.databinding.ActivityLanguageBinding

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding = ActivityLanguageBinding.inflate(layoutInflater)

        binding.toolbar.setOnClickListener {
            onBackPressed()
        }
    }
}