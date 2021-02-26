package com.matheussilas97.sensei.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.matheussilas97.sensei.database.dao.ClassDAO
import com.matheussilas97.sensei.database.dao.StudentsDAO
import com.matheussilas97.sensei.database.model.ClassModel
import com.matheussilas97.sensei.database.model.StudentsModel

@Database(entities = [StudentsModel::class, ClassModel::class], version = 1)
abstract class MainDataBase: RoomDatabase() {

//    abstract fun studentDAO(): StudentsDAO
//    abstract fun classDAO(): ClassDAO
//
//    companion object {
//        private lateinit var INSTANCE: MainDataBase
//        fun getDatabase(context: Context): MainDataBase {
//            if (!::INSTANCE.isInitialized) {
//                synchronized(MainDataBase::class.java) {
//                    INSTANCE = Room.databaseBuilder(context, MainDataBase::class.java, "bagDB")
//                        .allowMainThreadQueries()
//                        .build()
//                }
//            }
//            return INSTANCE
//        }
//    }
}