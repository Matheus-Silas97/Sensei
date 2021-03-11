package com.matheussilas97.sensei.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.database.model.StudentsModel
import com.matheussilas97.sensei.databinding.ItemCallStudentsBinding
import com.matheussilas97.sensei.databinding.ItemRankingBinding

class RankingAdapter(private val context: Context, private val list: List<StudentsModel>) :
    RecyclerView.Adapter<RankingAdapter.RankingViewHolder>() {

    inner class RankingViewHolder(binding: ItemRankingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val position = binding.position
        val name = binding.name
        val presence = binding.presences
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val binding = ItemRankingBinding.inflate(LayoutInflater.from(context), parent, false)
        return RankingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
       val p = position+1
        when (p) {
            1 -> {
                holder.position.setTextColor(ContextCompat.getColor(context, R.color.gold))
            }
            2 -> {
                holder.position.setTextColor(ContextCompat.getColor(context, R.color.silver))
            }
            3 -> {
                holder.position.setTextColor(ContextCompat.getColor(context, R.color.bronze))
            }
        }

        holder.position.text = p.toString()
        holder.name.text = list[position].name
        holder.presence.text = list[position].presence.toString()
    }

    override fun getItemCount(): Int = list.size

}