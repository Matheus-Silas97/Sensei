package com.matheussilas97.sensei.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matheussilas97.sensei.database.model.StudentsModel

class StudentsAdapter(private val context: Context,private val list: List<StudentsModel> ):
    RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder>() {

    inner class StudentsViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = list.size
}