package com.matheussilas97.sensei.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.adapter.CallListAdapter
import com.matheussilas97.sensei.databinding.ActivityCallBinding
import com.matheussilas97.sensei.util.BaseActvity
import com.matheussilas97.sensei.util.Constants
import com.matheussilas97.sensei.viewmodel.StudentsViewModel
import kotlin.properties.Delegates

class CallActivity : BaseActvity() {

    private lateinit var binding: ActivityCallBinding
    private lateinit var viewModel: StudentsViewModel

    private var idGroup by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[StudentsViewModel::class.java]

        onClick()
        callList()
        takeValuesFromId()

    }

    private fun onClick() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.listFromGroup(idGroup)
    }

    private fun takeValuesFromId() {
        val extras = intent.extras
        if (extras != null) {
            val groupName = extras.getString(Constants.GROUP_NAME)
            idGroup = extras.getInt(Constants.GROUP_ID)
            binding.toolbar.title = groupName

            viewModel.listFromGroup(idGroup)
        }
    }

    private fun callList() {
        viewModel.studentListFromGroup.observe(this, Observer { data ->
            if (!data.isNullOrEmpty()) {
                val adapter = CallListAdapter(this, data)
                binding.recyclerCall.layoutManager = LinearLayoutManager(this)
                binding.recyclerCall.adapter = adapter

                adapter.addOnItemClickListener(object : CallListAdapter.OnItemClickListener {
                    override fun onClick(id: Int, position: Int) {
                        viewModel.updateListCall(id)
                        toast(getString(R.string.call_info))
                    }

                    override fun onRemovePresence(id: Int, position: Int) {
                        viewModel.removePresence(id)
                        toast(getString(R.string.remove_presence))
                    }
                })
            } else {
                setNoResultAdapter(this, binding.recyclerCall, getString(R.string.no_students))
            }
        })
    }
}