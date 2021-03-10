package com.matheussilas97.sensei.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matheussilas97.sensei.database.model.ClassModel
import com.matheussilas97.sensei.database.model.StudentsModel
import com.matheussilas97.sensei.database.repository.ClassRepository
import com.matheussilas97.sensei.database.repository.StudentsRepository

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val mClassRepository = ClassRepository(application.applicationContext)
    private val mStudentRepository = StudentsRepository(application.applicationContext)

    private val mRankingList = MutableLiveData<List<StudentsModel>>()
    val rankingList: LiveData<List<StudentsModel>> = mRankingList

    fun deleteAll() {
        mClassRepository.deleteAll()
        mStudentRepository.deleteAllStudents()
    }

    fun ranking() {
        mRankingList.value = mClassRepository.ranking()
    }
}
