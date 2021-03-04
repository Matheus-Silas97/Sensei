package com.matheussilas97.sensei.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.matheussilas97.sensei.database.repository.ClassRepository
import com.matheussilas97.sensei.database.repository.StudentsRepository

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val mClassRepository = ClassRepository(application.applicationContext)
    private val mStudentRepository = StudentsRepository(application.applicationContext)

    fun deleteAll() {
        mClassRepository.deleteAll()
        mStudentRepository.deleteAllStudents()
    }

}