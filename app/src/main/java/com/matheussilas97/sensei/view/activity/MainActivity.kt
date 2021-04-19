package com.matheussilas97.sensei.view.activity

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.databinding.ActivityMainBinding
import com.matheussilas97.sensei.util.Constants
import com.matheussilas97.sensei.util.SharedPreferences
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadLanguage()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val naviController = findNavController(R.id.nav_host)

        binding.bottomNavigationView.setupWithNavController(naviController)


    }

    override fun recreate() {
        super.recreate()
        loadLanguage()
    }

    private fun loadLanguage() {
        val languageToLoad =
            SharedPreferences.getInstance(this).getString(Constants.LANGUAGE, "")
        val locale = Locale(languageToLoad!!)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        this.resources.updateConfiguration(
            config,
            this.resources.displayMetrics
        )
    }

}