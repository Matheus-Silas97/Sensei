package com.matheussilas97.sensei.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matheussilas97.sensei.database.model.ClassModel
import com.matheussilas97.sensei.database.repository.ClassRepository

class GroupsViewModel(application: Application) : AndroidViewModel(application) {

    private val mClassRepository = ClassRepository(application.applicationContext)

    private var mSaveItem = MutableLiveData<Boolean>()
    val saveGroup: LiveData<Boolean> = mSaveItem

    private val mGroupList = MutableLiveData<List<ClassModel>>()
    val groupList: LiveData<List<ClassModel>> = mGroupList

    fun list() {
        mGroupList.value = mClassRepository.listClass()
    }

    fun deleteClass(id: Int) {
        val group = mClassRepository.getLoad(id)
        if (group != null) {
            mClassRepository.deleteClass(group)
            mClassRepository.deleteStudentByClass(id)
        }
    }

    fun deleteAllClass() {
        mClassRepository.deleteAll()
    }


    fun saveClass(group: ClassModel) {
        mSaveItem.value = mClassRepository.addClass(group)
    }

    fun totalGroups(): Int = mClassRepository.totalGroups()

    fun totalStudents(): Int = mClassRepository.totalStudents()


}