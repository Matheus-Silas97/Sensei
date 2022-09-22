package com.matheussilas97.sensei.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matheussilas97.sensei.R
import com.matheussilas97.sensei.database.model.ClassModel
import com.matheussilas97.sensei.database.repository.ClassRepository

class GroupsViewModel(application: Application) : AndroidViewModel(application) {

    private val mClassRepository = ClassRepository(application.applicationContext)

    private var mSaveItem = MutableLiveData<Boolean>()
    val saveGroup: LiveData<Boolean> = mSaveItem
    val addClassErro = MutableLiveData<String>()

    private val mGroupList = MutableLiveData<List<ClassModel>>()
    val groupList: LiveData<List<ClassModel>> = mGroupList

    fun list() {
        mGroupList.value = mClassRepository.listClass().sortedBy { it.className }
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
        if (group.id == 0) {
            mSaveItem.value = mClassRepository.addClass(group)
        } else {
            mSaveItem.value = mClassRepository.updateClass(group)
        }

    }

    fun totalGroups(): Int = mClassRepository.totalGroups()

    fun totalStudents(): Int = mClassRepository.totalStudents()

    fun totalWomens(gender: String): Int = mClassRepository.totalWomens(gender)

    fun totalMens(gender: String): Int = mClassRepository.totalMens(gender)

    fun validadeAddClass(nameClass: String, context: Context): Boolean {
        return if (nameClass.isEmpty()) {
            addClassErro.value = context.getString(R.string.empty_name_group)
            false
        }else{
            true
        }
    }

}