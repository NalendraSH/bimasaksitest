package com.bimasaktitest.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bimasaktitest.local.dao.MainDao
import com.bimasaktitest.main.MainModel

@Database(entities = [MainModel.Data::class], version = 1)
abstract class RoomManager : RoomDatabase() {

    abstract fun mainDao(): MainDao

    companion object{

        private var instance: RoomManager? = null

        fun getInstance(context: Context): RoomManager? {
            if (instance == null){
                synchronized(RoomManager::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomManager::class.java,
                        "database.db").build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

    }
}