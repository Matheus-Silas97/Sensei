package com.matheussilas97.sensei.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.databinding.ActivityLanguageBinding
import com.matheussilas97.sensei.util.Constants
import com.matheussilas97.sensei.util.SharedPreferences

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setOnClickListener {
            onBackPressed()
        }

        binding.btnChangeLanguage.setOnClickListener {
            if (binding.radioSpain.isChecked) {
            SharedPreferences.getInstance(this).saveString(Constants.LANGUAGE, Constants.SPANISH)
                toast(getString(R.string.language_spanish))
                onBackPressed()
            } else if (binding.radioUsa.isChecked) {
                SharedPreferences.getInstance(this).saveString(Constants.LANGUAGE, Constants.ENGLISH)
                toast(getString(R.string.language_english))
                onBackPressed()
            } else if (binding.radioBrazil.isChecked){
                SharedPreferences.getInstance(this).saveString(Constants.LANGUAGE, Constants.PORTUGUESE)
                toast(getString(R.string.language_portuguese))
                onBackPressed()
            }else{
                toast(getString(R.string.language_selected))
            }

        }
    }

    private fun toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}