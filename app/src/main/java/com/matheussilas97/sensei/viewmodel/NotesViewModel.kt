package com.matheussilas97.sensei.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matheussilas97.sensei.database.model.NotesModel
import com.matheussilas97.sensei.database.repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val mNotesRepository = NotesRepository(application.applicationContext)

    private var mSaveItem = MutableLiveData<Boolean>()
    val saveNote: LiveData<Boolean> = mSaveItem

    private val mNoteList = MutableLiveData<List<NotesModel>>()
    val noteList: LiveData<List<NotesModel>> = mNoteList

    fun listNotes() {
        mNoteList.value = mNotesRepository.listNotes()
    }

    fun deleteNote(id: Int) {
        val group = mNotesRepository.loadNote(id)
        if (group != null) {
            mNotesRepository.deleteNote(group)
        }
    }


    fun saveNote(note: NotesModel) {
        if (note.id == 0) {
            mSaveItem.value = mNotesRepository.addNote(note)
        } else {
            mSaveItem.value = mNotesRepository.updateNote(note)
        }

    }

}