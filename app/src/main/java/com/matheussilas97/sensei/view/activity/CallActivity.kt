package com.matheussilas97.sensei.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.adapter.CallListAdapter
import com.matheussilas97.sensei.databinding.ActivityCallBinding
import com.matheussilas97.sensei.util.Constants
import com.matheussilas97.sensei.viewmodel.StudentsViewModel
import kotlin.properties.Delegates

class CallActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCallBinding

    private lateinit var viewModel: StudentsViewModel

    private var idGroup by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[StudentsViewModel::class.java]

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        callList()
        takeValuesFromId()

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
                binding.textInfo.visibility = View.GONE
                binding.recyclerCall.visibility = View.VISIBLE

                val adapter = CallListAdapter(this, data)
                binding.recyclerCall.layoutManager = LinearLayoutManager(this)
                binding.recyclerCall.adapter = adapter

                adapter.addOnItemClickListener(object : CallListAdapter.OnItemClickListener {
                    override fun onClick(id: Int) {
                        viewModel.updateListCall(id)
                        viewModel.listFromGroup(idGroup)
                        Toast.makeText(applicationContext, R.string.call_info, Toast.LENGTH_SHORT).show()
                    }

                })

            }else{
                binding.textInfo.visibility = View.VISIBLE
                binding.recyclerCall.visibility = View.GONE
            }
        })
    }
}