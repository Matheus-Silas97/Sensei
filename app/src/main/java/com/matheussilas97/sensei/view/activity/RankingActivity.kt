package com.matheussilas97.sensei.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.adapter.RankingAdapter
import com.matheussilas97.sensei.databinding.ActivityRankingBinding
import com.matheussilas97.sensei.viewmodel.SettingsViewModel

class RankingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRankingBinding

    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]

        listRanking()
        viewModel.ranking()
    }

    override fun onResume() {
        super.onResume()
        viewModel.ranking()
    }

    private fun listRanking() {
        viewModel.rankingList.observe(this, Observer { data ->
            if (!data.isNullOrEmpty()) {
                binding.textInfoEmpty.visibility = View.GONE
                binding.recyclerRanking.visibility = View.VISIBLE
                binding.textInfo.visibility = View.VISIBLE
                binding.txtPlacing.visibility = View.VISIBLE
                binding.txtPresence.visibility = View.VISIBLE

                val adapter = RankingAdapter(this, data)
                binding.recyclerRanking.layoutManager = LinearLayoutManager(this)
                binding.recyclerRanking.adapter = adapter
            } else {
                binding.textInfoEmpty.visibility = View.VISIBLE
                binding.recyclerRanking.visibility = View.GONE
                binding.textInfo.visibility = View.GONE
                binding.txtPlacing.visibility = View.GONE
                binding.txtPresence.visibility = View.GONE
            }
        })
    }


}