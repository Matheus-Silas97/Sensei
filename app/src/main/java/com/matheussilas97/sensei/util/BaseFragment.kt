package com.matheussilas97.sensei.util

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matheussilas97.sensei.adapter.NoResultAdapter

abstract class BaseFragment : Fragment() {

    fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun setNoResultAdapter(context: Context, recyclerView: RecyclerView, message: String) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = NoResultAdapter(context, message)
        recyclerView.layoutManager = layoutManager
    }
}