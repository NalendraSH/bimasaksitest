package com.bimasaktitest.local.repositories

import android.app.Application
import com.bimasaktitest.local.RoomManager
import com.bimasaktitest.local.dao.MainDao
import com.bimasaktitest.main.MainModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainRepository(application: Application) {

    private var mainDao: MainDao

    init {
        val roomManager: RoomManager = RoomManager.getInstance(application.applicationContext)!!
        mainDao = roomManager.mainDao()
    }

    fun getAllLocalData(postValue: (List<MainModel.Data>) -> Unit) {
        GlobalScope.launch {
            val dataList = mainDao.getAll()
            postValue(dataList)
        }
    }

    fun getLocalDataById(id: Int, postValue: (MainModel.Data?) -> Unit) {
        GlobalScope.launch {
            val data = mainDao.getById(id)
            postValue(data)
        }
    }

    fun submitLocalData(data: MainModel.Data) {
        GlobalScope.launch {
            mainDao.insert(data)
        }
    }

    fun deleteAllData() {
        GlobalScope.launch {
            mainDao.deleteAll()
        }
    }

}