package com.matheussilas97.sensei.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matheussilas97.sensei.database.model.NotesModel
import com.matheussilas97.sensei.databinding.ItemNoteBinding

class NotesAdapter(private val context: Context, private val list: List<NotesModel>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        val note = binding.note
        val delete = binding.imgDelete
        val edit = binding.imgEdit
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.note.text = list[position].note

        holder.delete.setOnClickListener {
            onItemClickLister?.onDelete(list[position].id, list[position].note)
        }

        holder.edit.setOnClickListener {
            onItemClickLister?.onEdit(list[position].id, list[position].note)
        }
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener {
        fun onDelete(id: Int, note: String)
        fun onEdit(id: Int, note: String)
    }

    private var onItemClickLister: OnItemClickListener? = null

    fun addOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickLister = onItemClickListener
    }
}