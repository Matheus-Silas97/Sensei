package com.matheussilas97.sensei.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentsModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "grade")
    val grade: String,

    @ColumnInfo(name = "birthDate")
    val birthDate: String,

    @ColumnInfo(name = "phone")
    val phone: String,

    @ColumnInfo(name = "presence")
    val presence: Int,

    @ColumnInfo(name = "dateStart")
    val dateStart: String,

    @ColumnInfo(name = "classId")
    val classId: Int,

    @ColumnInfo(name = "gender")
    val gender: String

)
