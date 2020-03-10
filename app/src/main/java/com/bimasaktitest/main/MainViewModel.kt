package com.bimasaktitest.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bimasaktitest.network.repositories.MainRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class MainViewModel : ViewModel(), KoinComponent {

    private val networkRepository: MainRepository by inject{ parametersOf(viewModelScope) }
    private val localRepository: com.bimasaktitest.local.repositories.MainRepository by inject{ parametersOf(viewModelScope) }

    var liveDataList: MutableLiveData<MainModel.Response> = MutableLiveData()
    var localLiveDataList: MutableLiveData<List<MainModel.Data>> = MutableLiveData()
    var localLiveData: MutableLiveData<MainModel.Data?> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getListData() {
        isLoading.postValue(true)
        networkRepository.getListData({
            isLoading.postValue(false)
            liveDataList.postValue(it)
        }, {
            isLoading.postValue(false)
            Log.d("fuel_error", it.message.toString())
        })
    }

    fun getAllLocalData() {
        isLoading.postValue(true)
        localRepository.getAllLocalData {
            isLoading.postValue(false)
            localLiveDataList.postValue(it)
        }
    }

    fun getLocalDataById(id: Int) {
        localRepository.getLocalDataById(id) {
            localLiveData.postValue(it)
        }
    }

    fun submitLocalData(data: MainModel.Data) {
        localRepository.submitLocalData(data)
    }

    fun deleteAllData() {
        localRepository.deleteAllData()
    }
}