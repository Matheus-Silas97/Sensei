package com.matheussilas97.sensei.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matheussilas97.sensei.database.model.ClassModel

class ClassAdapter(private val context: Context, private val list: List<ClassModel>):
    RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {

    inner class ClassViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = list.size

}