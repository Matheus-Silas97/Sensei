package com.matheussilas97.sensei.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.matheussilas97.sensei.databinding.ItemNoResultBinding

class NoResultAdapter(private val context: Context, private val title: String) :
    RecyclerView.Adapter<NoResultAdapter.NoResultHolder>() {

    inner class NoResultHolder(binding: ItemNoResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val txtTitle = binding.tvTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoResultHolder {
        val binding = ItemNoResultBinding.inflate(LayoutInflater.from(context), parent, false)
        return NoResultHolder(binding)
    }

    override fun onBindViewHolder(holder: NoResultHolder, position: Int) {
        holder.txtTitle.text = title
    }

    override fun getItemCount(): Int {
        return 1
    }


}