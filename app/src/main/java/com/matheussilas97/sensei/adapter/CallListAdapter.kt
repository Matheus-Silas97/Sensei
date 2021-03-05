package com.matheussilas97.sensei.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matheussilas97.sensei.database.model.StudentsModel
import com.matheussilas97.sensei.databinding.ItemCallStudentsBinding
import com.matheussilas97.sensei.databinding.ItemStudentsBinding

class CallListAdapter(private val context: Context, private val list: List<StudentsModel>) :
    RecyclerView.Adapter<CallListAdapter.StudentsCallViewHolder>() {

    inner class StudentsCallViewHolder(binding: ItemCallStudentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val layout = binding.layout
        val name = binding.nameStudents
        val presence = binding.imgPresence
        val presenceNumber = binding.numberPresence

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsCallViewHolder {
        val binding = ItemCallStudentsBinding.inflate(LayoutInflater.from(context), parent, false)
        return StudentsCallViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentsCallViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.presenceNumber.text = list[position].presence.toString()

        holder.presence.setOnClickListener {
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