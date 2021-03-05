package com.matheussilas97.sensei.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.database.model.ClassModel
import com.matheussilas97.sensei.databinding.ItemClassBinding

class ClassAdapter(private val context: Context, private val list: List<ClassModel>) :
    RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {


    inner class ClassViewHolder(binding: ItemClassBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val nameGroup = binding.nameGroup
        val layoutClass = binding.layoutClassItem
        val deleteIcon = binding.imgDelete
        val editIcon = binding.imgEdit
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val binding = ItemClassBinding.inflate(LayoutInflater.from(context), parent, false)
        return ClassViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        holder.nameGroup.text = list[position].className

        holder.layoutClass.setOnClickListener {
            onItemClickLister?.onClick(list[position].id, list[position].className)
        }

        holder.deleteIcon.setOnClickListener {
            onItemClickLister?.onDelete(list[position].id, list[position].className)
        }

        holder.editIcon.setOnClickListener {
            onItemClickLister?.onEdit(list[position].id, list[position].className)
        }
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener {
        fun onClick(id: Int, nameGroup: String)
        fun onDelete(id: Int, nameGroup: String)
        fun onEdit(id: Int, nameGroup: String)
    }

    private var onItemClickLister: OnItemClickListener? = null

    fun addOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickLister = onItemClickListener
    }


}