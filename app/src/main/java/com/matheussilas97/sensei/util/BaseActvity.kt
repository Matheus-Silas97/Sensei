package com.matheussilas97.sensei.util

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActvity : AppCompatActivity() {

    fun getNextActivity(activity: Class<*>) {
        startActivity(Intent(this, activity))
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}