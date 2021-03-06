package com.matheussilas97.sensei.database.dao

import androidx.room.*
import com.matheussilas97.sensei.database.model.ClassModel
import com.matheussilas97.sensei.database.model.StudentsModel

@Dao
interface ClassDAO {

    @Query("SELECT * FROM groups")
    fun getAllGroups(): List<ClassModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroup(item: ClassModel): Long

    @Update
    fun updateGroup(item: ClassModel): Int

    @Delete
    fun deleteGroup(item: ClassModel)

    @Query("DELETE FROM students WHERE classId = :idClass")
    fun deleteStudentsByClass(idClass: Int)

    @Query("DELETE FROM groups")
    fun deleteAllGroups(): Int

    @Query("SELECT * FROM groups WHERE id = :id")
    fun load(id: Int): ClassModel?

    @Query("SELECT count() FROM students")
    fun totalStudents(): Int

    @Query("SELECT count() FROM groups")
    fun totalGroups(): Int

    @Query("SELECT count() FROM students WHERE gender = :gender")
    fun totalMens(gender: String): Int

    @Query("SELECT count() FROM students WHERE gender = :gender")
    fun totalWomens(gender: String): Int

    @Query("SELECT * FROM students ORDER BY presence DESC")
    fun ranking(): List<StudentsModel>


}