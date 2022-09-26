package com.matheussilas97.sensei.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.database.model.StudentsModel
import com.matheussilas97.sensei.database.repository.StudentsRepository
import java.text.SimpleDateFormat
import java.util.*

class StudentsViewModel(application: Application) : AndroidViewModel(application) {

    private val mStudentRepository = StudentsRepository(application.applicationContext)

    private var mSaveStudent = MutableLiveData<Boolean>()
    val saveStudent: LiveData<Boolean> = mSaveStudent

    private val mStudentList = MutableLiveData<List<StudentsModel>>()
    val studentList: LiveData<List<StudentsModel>> = mStudentList

    private val mStudentListFromGroup = MutableLiveData<List<StudentsModel>>()
    val studentListFromGroup: LiveData<List<StudentsModel>> = mStudentListFromGroup

    val addStudentsStatus = MutableLiveData<String>()

    fun list() {
        mStudentList.value = mStudentRepository.listStudents().sortedBy { it.name }
    }

    fun listFromGroup(id: Int) {
        mStudentListFromGroup.value = mStudentRepository.listFromGroup(id).sortedBy { it.name }
    }

    fun deleteStudent(id: Int) {
        val student = mStudentRepository.getLoadStudent(id)
        if (student != null) {
            mStudentRepository.deleteStudents(student)
        }
    }

    fun loadStudent(id: Int): StudentsModel {
        return mStudentRepository.getLoadStudent(id)
    }

    fun saveStudent(student: StudentsModel) {
        if (student.id == 0) {
            mSaveStudent.value = mStudentRepository.addStudents(student)
        } else {
            mSaveStudent.value = mStudentRepository.updateStudents(student)
        }
    }


    fun validateAddStudent(model: StudentsModel, context: Context): Boolean {
        return if (model.name.isEmpty()) {
            addStudentsStatus.value = context.getString(R.string.empty_name)
            false
        } else if (model.gender.isEmpty()) {
            addStudentsStatus.value = context.getString(R.string.empty_gender)
            false
        } else if (model.birthDate.isEmpty()) {
            addStudentsStatus.value = context.getString(R.string.empty_birthday)
            false
        } else if (model.grade.isEmpty()) {
            addStudentsStatus.value = context.getString(R.string.empty_grade)
            false
        } else if (model.phone.isEmpty()) {
            addStudentsStatus.value = context.getString(R.string.empty_phone)
            false
        } else if (model.dateStart.isEmpty()) {
            addStudentsStatus.value = context.getString(R.string.empty_date_start)
            false
        } else {
            true
        }
    }

    fun updatePresenceAmount(amount: Int, id: Int) {
        val student = mStudentRepository.getLoadStudent(id)

        val updateStudent = StudentsModel(
            student.id,
            student.name,
            student.grade,
            student.birthDate,
            student.phone,
            amount,
            student.dateStart,
            student.classId,
            student.gender
        )
        mStudentRepository.updateStudents(updateStudent)
    }

    fun updateListCall(id: Int) {
        val student = mStudentRepository.getLoadStudent(id)

        val presence = student.presence + 1

        val updateStudent = StudentsModel(
            student.id,
            student.name,
            student.grade,
            student.birthDate,
            student.phone,
            presence,
            student.dateStart,
            student.classId,
            student.gender
        )
        mStudentRepository.updateStudents(updateStudent)
    }

    fun removePresence(id: Int) {
        val student = mStudentRepository.getLoadStudent(id)

        val presence = student.presence - 1

        val updateStudent = StudentsModel(
            student.id,
            student.name,
            student.grade,
            student.birthDate,
            student.phone,
            presence,
            student.dateStart,
            student.classId,
            student.gender
        )
        mStudentRepository.updateStudents(updateStudent)
    }

    fun countStudentsFromGroups(idGroup: Int): Int {
        return mStudentRepository.countStudentsFromGroups(idGroup)
    }

    fun calculateAge(birthDate: String): Int {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val birthday: Date = sdf.parse(birthDate)
        val dataNascimento: Calendar = Calendar.getInstance()
        dataNascimento.time = birthday
        val hoje: Calendar = Calendar.getInstance()
        var idade: Int = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR)
        if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
            idade--
        } else {
            if (hoje.get(Calendar.MONTH) === dataNascimento.get(Calendar.MONTH) && hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(
                    Calendar.DAY_OF_MONTH
                )
            ) {
                idade--
            }
        }
        return idade
    }
}