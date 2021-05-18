    package com.matheussilas97.sensei.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.databinding.ActivityLanguageBinding
import com.matheussilas97.sensei.util.BaseActvity
import com.matheussilas97.sensei.util.Constants
import com.matheussilas97.sensei.util.SharedPreferences

class LanguageActivity : BaseActvity() {

    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setTitle(R.string.change_language)

        onClick()
    }

    private fun onClick() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.btnChangeLanguage.setOnClickListener {
            changeLanguage()
        }
    }

    private fun changeLanguage() {
        when {
            binding.radioSpain.isChecked -> {
                SharedPreferences.getInstance(this)
                    .saveString(Constants.LANGUAGE, Constants.SPANISH)
                toast(getString(R.string.language_spanish))
                getNextActivity(MainActivity::class.java)
                finish()
            }
            binding.radioUsa.isChecked -> {
                SharedPreferences.getInstance(this)
                    .saveString(Constants.LANGUAGE, Constants.ENGLISH)
                toast(getString(R.string.language_english))
                getNextActivity(MainActivity::class.java)
                finish()
            }
            binding.radioBrazil.isChecked -> {
                SharedPreferences.getInstance(this)
                    .saveString(Constants.LANGUAGE, Constants.PORTUGUESE)
                toast(getString(R.string.language_portuguese))
                getNextActivity(MainActivity::class.java)
                finish()
            }
            else -> {
                toast(getString(R.string.language_selected))
            }
        }
    }

}