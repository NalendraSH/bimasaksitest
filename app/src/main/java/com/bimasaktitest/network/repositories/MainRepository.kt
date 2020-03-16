package com.bimasaktitest.network.repositories

import com.bimasaktitest.data.EndPoint
import com.bimasaktitest.main.models.MainModel
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.awaitObjectResponseResult
import kotlinx.coroutines.runBlocking

class MainRepository {

    fun getListData(onSuccess: (MainModel.Response) -> Unit, onError: (FuelError) -> Unit){
        runBlocking {
            val (_, _, result) = Fuel.get(EndPoint.LIST_DATA).awaitObjectResponseResult(
                MainModel.Response.Deserializer())
            result.fold({
                onSuccess(it)
            }, {
                onError(it)
            })
        }
    }
}