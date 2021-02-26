package com.matheussilas97.sensei.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.adapter.ClassAdapter
import com.matheussilas97.sensei.database.model.ClassModel
import com.matheussilas97.sensei.databinding.FragmentGroupsBinding
import com.matheussilas97.sensei.view.activity.ClassAddActivity
import com.matheussilas97.sensei.view.activity.StudentActivity
import com.matheussilas97.sensei.viewmodel.ClassViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GroupsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentGroupsBinding

    private lateinit var viewModel: ClassViewModel

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
        binding = FragmentGroupsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(ClassViewModel::class.java)

        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(context, ClassAddActivity::class.java))
        }

        buildList()
        return binding.root
    }

    private fun buildList() {
        val list = arrayListOf<ClassModel>()
        list.add(ClassModel(1, "Teste"))
        list.add(ClassModel(1, "Teste"))
        list.add(ClassModel(1, "Teste"))
        list.add(ClassModel(1, "Teste"))
        val adapter = ClassAdapter(requireContext(), list)
        binding.recyclerClass.layoutManager = LinearLayoutManager(context)
        binding.recyclerClass.adapter = adapter

        adapter.addOnItemClickListener(object : ClassAdapter.OnItemClickListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, StudentActivity::class.java)

                startActivity(intent)
            }

        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GroupsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}