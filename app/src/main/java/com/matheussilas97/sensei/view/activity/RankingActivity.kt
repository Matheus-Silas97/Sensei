package com.matheussilas97.sensei.view.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
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

        binding.toolbar.inflateMenu(R.menu.menu_info)

        viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]

        click()
        listRanking()
        viewModel.ranking()

    }

    override fun onResume() {
        super.onResume()
        viewModel.ranking()
    }

    private fun click(){
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener menuToolbar(it)
        }

    }

    private fun menuToolbar(it: MenuItem): Boolean {
        when (it.itemId) {
            R.id.ic_info -> {
                info()
                return true
            }
            else -> {
                return false
            }
        }
    }

    private fun info() {
        val alertDialog = android.app.AlertDialog.Builder(this).create()
        alertDialog.setTitle(R.string.ranking)
        alertDialog.setMessage(getString(R.string.ranking_info))
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok),
            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        alertDialog.show()
    }



    private fun listRanking() {
        viewModel.rankingList.observe(this, Observer { data ->
            if (!data.isNullOrEmpty()) {
                binding.textInfoEmpty.visibility = View.GONE
                binding.recyclerRanking.visibility = View.VISIBLE
                binding.txtPlacing.visibility = View.VISIBLE
                binding.txtPresence.visibility = View.VISIBLE

                val adapter = RankingAdapter(this, data)
                binding.recyclerRanking.layoutManager = LinearLayoutManager(this)
                binding.recyclerRanking.adapter = adapter
            } else {
                binding.textInfoEmpty.visibility = View.VISIBLE
                binding.recyclerRanking.visibility = View.GONE
                binding.txtPlacing.visibility = View.GONE
                binding.txtPresence.visibility = View.GONE
            }
        })
    }


}