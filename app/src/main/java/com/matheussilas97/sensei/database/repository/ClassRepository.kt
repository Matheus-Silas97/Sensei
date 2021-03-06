package com.matheussilas97.sensei.database.repository

import android.content.Context
import com.matheussilas97.sensei.database.MainDataBase
import com.matheussilas97.sensei.database.model.ClassModel
import com.matheussilas97.sensei.database.model.StudentsModel

class ClassRepository(context: Context) {

    //Access to database
    private val mDataBase = MainDataBase.getDatabase(context).classDAO()

    fun listClass(): List<ClassModel> = mDataBase.getAllGroups()

    fun addClass(group: ClassModel): Boolean = mDataBase.insertGroup(group) > 0

    fun updateClass(group: ClassModel): Boolean = mDataBase.updateGroup(group) > 0

    fun deleteClass(group: ClassModel) = mDataBase.deleteGroup(group)

    fun deleteStudentByClass(id: Int) = mDataBase.deleteStudentsByClass(id)

    fun deleteAll(): Boolean = mDataBase.deleteAllGroups() > 0

    fun getLoad(id: Int): ClassModel? = mDataBase.load(id)

    fun totalGroups(): Int = mDataBase.totalGroups()

    fun totalStudents(): Int = mDataBase.totalStudents()

    fun totalWomens(gender: String): Int = mDataBase.totalWomens(gender)

    fun totalMens(gender: String): Int = mDataBase.totalMens(gender)

    fun ranking(): List<StudentsModel> = mDataBase.ranking()

}