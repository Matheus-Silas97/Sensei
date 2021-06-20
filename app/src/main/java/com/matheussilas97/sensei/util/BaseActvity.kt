package com.matheussilas97.sensei.util

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matheussilas97.sensei.adapter.NoResultAdapter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

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

    open fun emailValidator(email: String?): Boolean {
        val pattern: Pattern
        val emailVal = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
        pattern = Pattern.compile(emailVal)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    open fun convertDateFormat(
        date: String?,
        initDateFormat: String?,
        endDateFormat: String?
    ): String? {
        return try {
            val initDate = SimpleDateFormat(initDateFormat).parse(date)
            val formatter = SimpleDateFormat(endDateFormat)
            formatter.format(initDate)
        } catch (e: ParseException) {
            e.printStackTrace()
            "Erro ao obter data"
        }
    }
}