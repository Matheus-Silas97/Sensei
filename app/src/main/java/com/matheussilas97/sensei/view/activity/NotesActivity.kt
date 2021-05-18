package com.matheussilas97.sensei.view.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.adapter.NotesAdapter
import com.matheussilas97.sensei.database.model.NotesModel
import com.matheussilas97.sensei.databinding.ActivityNotesBinding
import com.matheussilas97.sensei.databinding.DialogDeleteBinding
import com.matheussilas97.sensei.databinding.DialogNoteAddBinding
import com.matheussilas97.sensei.util.BaseActvity
import com.matheussilas97.sensei.viewmodel.NotesViewModel

class NotesActivity : BaseActvity() {

    private lateinit var binding: ActivityNotesBinding

    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        binding.toolbar.inflateMenu(R.menu.menu_info)

        onClick()
        notesList()
    }

    override fun onResume() {
        super.onResume()
        viewModel.listNotes()
    }

    private fun optionsToolbar(it: MenuItem): Boolean {
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

    private fun onClick() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener {
            addNote()
        }

        binding.toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener optionsToolbar(it)
        }
    }

    private fun info(){
        val alertDialog = android.app.AlertDialog.Builder(this).create()
        alertDialog.setTitle(R.string.notes)
        alertDialog.setMessage(getString(R.string.notes_info))
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok),
            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        alertDialog.show()
    }

    private fun notesList() {
        viewModel.noteList.observe(this, Observer { data ->
            if (!data.isNullOrEmpty()) {
                binding.recyclerNotes.visibility = View.VISIBLE
                binding.textInfo.visibility = View.GONE

                val adapter = NotesAdapter(this, data)
                binding.recyclerNotes.layoutManager = LinearLayoutManager(this)
                binding.recyclerNotes.adapter = adapter

                adapter.addOnItemClickListener(object : NotesAdapter.OnItemClickListener {
                    override fun onDelete(id: Int, note: String) {
                        deleteNote(id)
                    }

                    override fun onEdit(id: Int, note: String) {
                        editNote(id, note)
                    }

                })
            } else {
                binding.recyclerNotes.visibility = View.GONE
                binding.textInfo.visibility = View.VISIBLE
            }

        })

    }

    private fun addNote() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        val binding: DialogNoteAddBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this), R.layout.dialog_note_add, null, false
        )
        binding.txtInfo.setText(R.string.add_note)

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
            val note = binding.editNote.text.toString()
            if (note.isNullOrEmpty()) {
                toast(getString(R.string.empty_note))
            } else {
                viewModel.saveNote(NotesModel(0, note))
                viewModel.listNotes()
                dialog.dismiss()
            }
        }
    }

    private fun editNote(id: Int, note: String) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        val binding: DialogNoteAddBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this), R.layout.dialog_note_add, null, false
        )
        binding.txtInfo.setText(R.string.edit_note)
        binding.editNote.setText(note)

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
            val newNote = binding.editNote.text.toString()
            if (newNote.isNullOrEmpty()) {
                toast(getString(R.string.empty_note))
            } else {
                viewModel.saveNote(NotesModel(id, newNote))
                viewModel.listNotes()
                dialog.dismiss()
            }

        }
    }

    private fun deleteNote(id: Int) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        val binding: DialogDeleteBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this), R.layout.dialog_delete, null, false
        )
        binding.txtDelete.setText(R.string.delete_note)

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
            viewModel.deleteNote(id)
            viewModel.listNotes()
            dialog.dismiss()
        }
    }

}