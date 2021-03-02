package com.matheussilas97.sensei.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matheussilas97.sensei.database.model.StudentsModel
import com.matheussilas97.sensei.databinding.ItemStudentsBinding

class StudentsAdapter(private val context: Context, private val list: List<StudentsModel>) :
    RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder>() {

    inner class StudentsViewHolder(binding: ItemStudentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val layout = binding.layout
        val name = binding.nameStudents
        val grade = binding.gradeStudents

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        val binding = ItemStudentsBinding.inflate(LayoutInflater.from(context), parent, false)
        return StudentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.grade.text = list[position].grade

        holder.layout.setOnClickListener {
            onItemClickLister?.onClick(list[position].id)
        }
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener {
        fun onClick(id: Int)
    }

    private var onItemClickLister: OnItemClickListener? = null

    fun addOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickLister = onItemClickListener
    }
}