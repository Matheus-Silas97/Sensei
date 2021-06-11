package com.matheussilas97.sensei.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.adapter.ClassAdapter
import com.matheussilas97.sensei.database.model.ClassModel
import com.matheussilas97.sensei.databinding.DialogDeleteBinding
import com.matheussilas97.sensei.databinding.DialogEditGroupBinding
import com.matheussilas97.sensei.databinding.FragmentGroupsBinding
import com.matheussilas97.sensei.util.BaseFragment
import com.matheussilas97.sensei.util.Constants
import com.matheussilas97.sensei.view.activity.ClassAddActivity
import com.matheussilas97.sensei.view.activity.StudentActivity
import com.matheussilas97.sensei.viewmodel.GroupsViewModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GroupsFragment : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentGroupsBinding

    private lateinit var viewModel: GroupsViewModel

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(GroupsViewModel::class.java)

        viewModel.list()

        onClick()
        buildList()
        observer()

    }

    private fun onClick() {
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(context, ClassAddActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.list()
    }

    private fun buildList() {
        viewModel.groupList.observe(viewLifecycleOwner, Observer { data ->
            if (!data.isNullOrEmpty()) {
                val adapter = ClassAdapter(requireContext(), data)
                binding.recyclerClass.layoutManager = LinearLayoutManager(context)
                binding.recyclerClass.adapter = adapter

                adapter.addOnItemClickListener(object : ClassAdapter.OnItemClickListener {
                    override fun onClick(id: Int, nameGroup: String) {
                        goToStudentsActivity(nameGroup, id)
                    }

                    override fun onDelete(id: Int, nameGroup: String) {
                        deleteGroup(id, nameGroup)
                    }

                    override fun onEdit(id: Int, nameGroup: String) {
                        editGroup(id, nameGroup)
                    }

                })
            } else {
                setNoResultAdapter(
                    requireContext(),
                    binding.recyclerClass,
                    getString(R.string.click_button_plus)
                )
            }

        })

    }

    private fun goToStudentsActivity(nameGroup: String, id: Int) {
        val intent = Intent(context, StudentActivity::class.java)
        intent.putExtra(Constants.GROUP_NAME, nameGroup)
        intent.putExtra(Constants.GROUP_ID, id)
        startActivity(intent)
    }

    private fun editGroup(id: Int, nameGroup: String) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        val binding: DialogEditGroupBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.dialog_edit_group, null, false
        )
        binding.editNameGroup.setText(nameGroup)
        builder.setView(binding.root)
        val dialog = builder.show()
        dialog.setCancelable(true)
        binding.btnClose.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnSave.setOnClickListener {
            val model = ClassModel(id, binding.editNameGroup.text.toString())
            viewModel.saveClass(model)
            viewModel.list()
            dialog.dismiss()
        }
    }

    private fun observer() {
        viewModel.saveGroup.observe(viewLifecycleOwner, Observer {
            if (it) {
                showToast(getString(R.string.update_data_sucess))
            } else {
                showToast(getString(R.string.error_message))
            }
        })
    }

    private fun deleteGroup(id: Int, nameGroup: String) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        val binding: DialogDeleteBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.dialog_delete, null, false
        )
        val msg = getString(R.string.delete_group_message)
        binding.txtDelete.text = "$msg $nameGroup?"

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
            viewModel.deleteClass(id)
            viewModel.list()
            dialog.dismiss()
        }
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