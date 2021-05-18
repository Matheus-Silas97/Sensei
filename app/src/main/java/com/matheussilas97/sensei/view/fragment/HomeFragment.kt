package com.matheussilas97.sensei.view.fragment

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheussilas97.sensei.adapter.CallClassAdapter
import com.matheussilas97.sensei.databinding.FragmentHomeBinding
import com.matheussilas97.sensei.util.BaseFragment
import com.matheussilas97.sensei.util.Constants
import com.matheussilas97.sensei.util.SharedPreferences
import com.matheussilas97.sensei.view.activity.CallActivity
import com.matheussilas97.sensei.viewmodel.GroupsViewModel
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewModel: GroupsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        loadLanguage()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(GroupsViewModel::class.java)

        viewModel.list()

        listCall()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.list()

        loadLanguage()
    }

    private fun loadLanguage() {
        val languageToLoad =
            SharedPreferences.getInstance(requireContext()).getString(Constants.LANGUAGE, "")
        val locale = Locale(languageToLoad)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        this.resources.updateConfiguration(
            config,
            this.resources.displayMetrics
        )
    }


    private fun listCall() {
        viewModel.groupList.observe(viewLifecycleOwner, Observer { data ->
            if (!data.isNullOrEmpty()) {
                binding.recyclerCall.visibility = View.VISIBLE
                binding.textInfoCall.visibility = View.VISIBLE
                binding.textInfo.visibility = View.GONE

                val adapter = CallClassAdapter(requireContext(), data)
                binding.recyclerCall.layoutManager = LinearLayoutManager(context)
                binding.recyclerCall.adapter = adapter

                adapter.addOnItemClickListener(object : CallClassAdapter.OnItemClickListener {
                    override fun onClick(id: Int, nameGroup: String) {
                        val intent = Intent(context, CallActivity::class.java)
                        intent.putExtra(Constants.GROUP_NAME, nameGroup)
                        intent.putExtra(Constants.GROUP_ID, id)
                        startActivity(intent)
                    }

                })
            }else{
                binding.recyclerCall.visibility = View.GONE
                binding.textInfoCall.visibility = View.GONE
                binding.textInfo.visibility = View.VISIBLE
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}