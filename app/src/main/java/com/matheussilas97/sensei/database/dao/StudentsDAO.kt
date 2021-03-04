package com.matheussilas97.sensei.database.dao

import androidx.room.*
import com.matheussilas97.sensei.database.model.ClassModel
import com.matheussilas97.sensei.database.model.StudentsModel

@Dao
interface StudentsDAO {

    @Query("SELECT * FROM students")
    fun getAllStudents(): List<StudentsModel>

    @Query("SELECT * FROM students WHERE classId = :id")
    fun listFromGroup(id: Int): List<StudentsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(item: StudentsModel): Long

    @Update
    fun updateStudent(item: StudentsModel): Int

    @Delete
    fun deleteStudent(item: StudentsModel)

    @Query("DELETE FROM students")
    fun deleteAllStudents(): Int

    @Query("SELECT * FROM students WHERE id = :id")
    fun loadStudent(id: Int): StudentsModel

    @Query("SELECT count() from students WHERE classId = :idGroup")
    fun countStudentsFromGroups(idGroup: Int): Int

}