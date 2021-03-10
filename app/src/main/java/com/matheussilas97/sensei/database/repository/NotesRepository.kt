package com.matheussilas97.sensei.database.repository

import android.content.Context
import com.matheussilas97.sensei.database.MainDataBase
import com.matheussilas97.sensei.database.model.NotesModel

class NotesRepository(context: Context) {

    //Access to database
    private val mDataBase = MainDataBase.getDatabase(context).notesDAO()

    fun listNotes(): List<NotesModel> = mDataBase.getNotes()

    fun addNote(note: NotesModel): Boolean = mDataBase.insertNote(note) > 0

    fun updateNote(note: NotesModel): Boolean = mDataBase.updateNote(note) > 0

    fun deleteNote(note: NotesModel) = mDataBase.deleteNote(note)

    fun loadNote(id: Int): NotesModel? = mDataBase.loadNote(id)
}