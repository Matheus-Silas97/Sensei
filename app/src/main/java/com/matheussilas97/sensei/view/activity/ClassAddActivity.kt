package com.matheussilas97.sensei.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.database.model.ClassModel
import com.matheussilas97.sensei.databinding.ActivityClassAddBinding
import com.matheussilas97.sensei.viewmodel.GroupsViewModel

class ClassAddActivity : AppCompatActivity() {

    private lateinit var viewModel: GroupsViewModel

    private lateinit var binding: ActivityClassAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityClassAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GroupsViewModel::class.java]

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.btnAdd.setOnClickListener {
            addGroup()
        }

        observer()
    }

    private fun addGroup() {
        val editGroupName = binding.editGroupName.text.toString()
        if (editGroupName.isEmpty()) {
            toast(getString(R.string.empty_name_group))
        } else {
            val group = ClassModel(0, editGroupName)
            viewModel.saveClass(group)
            onBackPressed()
        }
    }

    private fun observer(){
        viewModel.saveGroup.observe(this, Observer {
            if (it){
                toast(getString(R.string.add_group_sucess))
            }else{
                toast(getString(R.string.error_message))
            }
        })
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}