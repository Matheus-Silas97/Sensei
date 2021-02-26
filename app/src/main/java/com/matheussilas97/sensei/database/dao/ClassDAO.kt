package com.matheussilas97.sensei.database.dao

import androidx.room.*
import com.matheussilas97.sensei.database.model.ClassModel

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

    @Query("DELETE FROM groups")
    fun deleteAllGroups(): Int


}