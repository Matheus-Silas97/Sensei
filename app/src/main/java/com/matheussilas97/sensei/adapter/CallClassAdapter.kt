package com.matheussilas97.sensei.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matheussilas97.sensei.database.model.ClassModel
import com.matheussilas97.sensei.databinding.ItemCallClassBinding

class CallClassAdapter (private val context: Context, private val list: List<ClassModel>) :
    RecyclerView.Adapter<CallClassAdapter.ClassCallViewHolder>() {


    inner class ClassCallViewHolder(binding: ItemCallClassBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val nameGroup = binding.nameGroup
        val layoutClass = binding.layoutClassItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassCallViewHolder {
        val binding = ItemCallClassBinding.inflate(LayoutInflater.from(context), parent, false)
        return ClassCallViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassCallViewHolder, position: Int) {
        holder.nameGroup.text = list[position].className

        holder.layoutClass.setOnClickListener {
            onItemClickLister?.onClick(list[position].id, list[position].className)
        }

    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener {
        fun onClick(id: Int, nameGroup: String)
    }

    private var onItemClickLister: OnItemClickListener? = null

    fun addOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickLister = onItemClickListener
    }


}