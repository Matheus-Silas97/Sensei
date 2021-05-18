package com.matheussilas97.sensei.view.activity

import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.databinding.ActivityImcBinding
import com.matheussilas97.sensei.util.BaseActvity
import com.matheussilas97.sensei.viewmodel.SettingsViewModel
import java.text.DecimalFormat


class ImcActivity : BaseActvity() {

    private lateinit var binding: ActivityImcBinding
    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]

        click()
        observer()

        binding.toolbar.inflateMenu(R.menu.menu_info)

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
        alertDialog.setTitle(R.string.imc)
        alertDialog.setMessage(getString(R.string.imc_info))
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok),
            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        alertDialog.show()
    }

    private fun observer() {
        viewModel.mValueImc.observe(this, Observer {
            val decimalFormat = DecimalFormat("0.00")
            binding.textValueImc.text = decimalFormat.format(it).toString()
        })
    }


    private fun click() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.btnCalculate.setOnClickListener {
            calculateImc()
        }

        binding.toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener menuToolbar(it)
        }
    }

    private fun calculateImc() {
        val height = binding.editHeight.text.toString()
        val weight = binding.editWeight.text.toString()

        if (!viewModel.validadeImc(height, weight, this)) {
            viewModel.validadeImcStatus.observe(this, Observer {
                toast(it)
            })
        } else {
            binding.textInfoImc.text =
                viewModel.calculateImc(height.toFloat(), weight.toFloat(), this)
        }
    }

}