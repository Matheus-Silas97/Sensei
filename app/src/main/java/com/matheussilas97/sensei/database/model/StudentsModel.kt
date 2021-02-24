package com.matheussilas97.sensei.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudentsModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "grade")
    val grade: String,

    @ColumnInfo(name = "age")
    val age: Int,

    @ColumnInfo(name = "phone")
    val phone: String,

    @ColumnInfo(name = "presence")
    val presence: Int,

    @ColumnInfo(name = "classId")
    val classId: Int

)
