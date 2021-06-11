package com.matheussilas97.sensei.util

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matheussilas97.sensei.adapter.NoResultAdapter

abstract class BaseActvity : AppCompatActivity() {

    fun getNextActivity(activity: Class<*>) {
        startActivity(Intent(this, activity))
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun setNoResultAdapter(context: Context, recyclerView: RecyclerView, message: String) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = NoResultAdapter(context, message)
        recyclerView.layoutManager = layoutManager
    }
}