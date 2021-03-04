package com.matheussilas97.sensei.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matheussilas97.sensei.database.model.StudentsModel
import com.matheussilas97.sensei.database.repository.StudentsRepository

class StudentsViewModel(application: Application) : AndroidViewModel(application) {

    private val mStudentRepository = StudentsRepository(application.applicationContext)

    private var mSaveStudent = MutableLiveData<Boolean>()
    val saveStudent: LiveData<Boolean> = mSaveStudent

    private val mStudentList = MutableLiveData<List<StudentsModel>>()
    val studentList: LiveData<List<StudentsModel>> = mStudentList

    private val mStudentListFromGroup = MutableLiveData<List<StudentsModel>>()
    val studentListFromGroup: LiveData<List<StudentsModel>> = mStudentListFromGroup

    fun list() {
        mStudentList.value = mStudentRepository.listStudents()
    }

    fun listFromGroup(id: Int) {
        mStudentListFromGroup.value = mStudentRepository.listFromGroup(id)
    }

    fun deleteStudent(id: Int) {
        val student = mStudentRepository.getLoadStudent(id)
        if (student != null) {
            mStudentRepository.deleteStudents(student)
        }
    }

    fun loadStudent(id: Int): StudentsModel{
       return mStudentRepository.getLoadStudent(id)
    }

    fun deleteAllStudent() {
        mStudentRepository.deleteAllStudents()
    }


    fun saveStudent(student: StudentsModel) {
        mSaveStudent.value = mStudentRepository.addStudents(student)
    }

    fun countStudentsFromGroups(idGroup: Int): Int {
       return mStudentRepository.countStudentsFromGroups(idGroup)
    }
}