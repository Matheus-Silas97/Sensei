package com.matheussilas97.sensei.database.repository

import android.content.Context
import com.matheussilas97.sensei.database.MainDataBase
import com.matheussilas97.sensei.database.model.StudentsModel

class StudentsRepository(context: Context) {

    //Access to database
    private val mDataBase = MainDataBase.getDatabase(context).studentDAO()

    fun listStudents(): List<StudentsModel> = mDataBase.getAllStudents()

    fun listFromGroup(id: Int): List<StudentsModel> = mDataBase.listFromGroup(id)

    fun addStudents(student: StudentsModel): Boolean = mDataBase.insertStudent(student) > 0

    fun updateStudents(student: StudentsModel): Boolean = mDataBase.updateStudent(student) > 0

    fun deleteStudents(student: StudentsModel) = mDataBase.deleteStudent(student)

    fun deleteAllStudents(): Boolean = mDataBase.deleteAllStudents() > 0

    fun getLoadStudent(id: Int): StudentsModel = mDataBase.loadStudent(id)

    fun countStudentsFromGroups(idGroup: Int): Int = mDataBase.countStudentsFromGroups(idGroup)

}