package com.matheussilas97.sensei.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.databinding.DialogDeleteBinding
import com.matheussilas97.sensei.databinding.FragmentSettingsBinding
import com.matheussilas97.sensei.view.activity.LanguageActivity
import com.matheussilas97.sensei.view.activity.NotesActivity
import com.matheussilas97.sensei.view.activity.RankingActivity
import com.matheussilas97.sensei.view.activity.ReportsActivity
import com.matheussilas97.sensei.viewmodel.SettingsViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SettingsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentSettingsBinding

    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)

        binding.btnNotes.setOnClickListener {
            startActivity(Intent(context, NotesActivity::class.java))
        }

        binding.btnRanking.setOnClickListener {
            startActivity(Intent(context, RankingActivity::class.java))
        }

        binding.btnChangeLanguage.setOnClickListener {
            startActivity(Intent(context, LanguageActivity::class.java))
        }

        binding.btnReports.setOnClickListener {
            startActivity(Intent(context, ReportsActivity::class.java))
        }

        binding.btnResetAll.setOnClickListener {
            resetAll()
        }

        return binding.root
    }

    private fun resetAll() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        val binding: DialogDeleteBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.dialog_delete, null, false
        )
        binding.txtDelete.setText(R.string.delete_all)
        binding.btnDelete.setText(R.string.reset)

        builder.setView(binding.root)
        val dialog = builder.show()
        dialog.setCancelable(true)
        binding.btnClose.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnDelete.setOnClickListener {
            viewModel.deleteAll()
            Toast.makeText(context, R.string.delete_all_sucess, Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}