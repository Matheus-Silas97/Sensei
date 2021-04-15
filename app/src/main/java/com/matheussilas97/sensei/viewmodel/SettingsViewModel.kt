package com.matheussilas97.sensei.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.database.model.ClassModel
import com.matheussilas97.sensei.database.model.StudentsModel
import com.matheussilas97.sensei.database.repository.ClassRepository
import com.matheussilas97.sensei.database.repository.StudentsRepository

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val mClassRepository = ClassRepository(application.applicationContext)
    private val mStudentRepository = StudentsRepository(application.applicationContext)

    private val mRankingList = MutableLiveData<List<StudentsModel>>()
    val rankingList: LiveData<List<StudentsModel>> = mRankingList

    val mValueImc = MutableLiveData<Float>()

    fun deleteAll() {
        mClassRepository.deleteAll()
        mStudentRepository.deleteAllStudents()
    }

    fun ranking() {
        mRankingList.value = mClassRepository.ranking()
    }

    fun calculateImc(height: Float, weight: Float, context: Context): String {
        var value = ""
        val total = weight / (height * height)

        mValueImc.value = total
        when {
            total <= 18.59 -> {
                value = context.getString(R.string.light_thinness)
            }
            total in 18.60..24.99 -> {
                value = context.getString(R.string.healthy)
            }
            total in 25.00..29.99 -> {
                value = context.getString(R.string.overweight)
            }
            total in 30.00..34.99 -> {
                value = context.getString(R.string.obesity_Grade_1)
            }
            total in 35.00..39.99 -> {
                value = context.getString(R.string.obesity_Grade_2)
            }
            else -> {
                value = context.getString(R.string.obesity_Grade_3)
            }
        }

        return value
    }

}
