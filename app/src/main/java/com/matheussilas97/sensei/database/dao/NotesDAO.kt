package com.matheussilas97.sensei.database.dao

import androidx.room.*
import com.matheussilas97.sensei.database.model.NotesModel

@Dao
interface NotesDAO {

    @Query("SELECT * FROM notes")
    fun getNotes(): List<NotesModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(item: NotesModel): Long

    @Update
    fun updateNote(item: NotesModel): Int

    @Delete
    fun deleteNote(item: NotesModel)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun loadNote(id: Int): NotesModel?
}