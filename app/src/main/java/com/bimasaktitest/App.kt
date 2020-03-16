package com.bimasaktitest

import android.app.Application
import androidx.annotation.Keep
import com.bimasaktitest.di.modules.appModule
import com.bimasaktitest.di.modules.mainModule
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.interceptors.LogResponseInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@Keep
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupFuel()
        setupKoin()
    }

    private fun setupFuel() {
        val fuelManager = FuelManager.instance
        fuelManager.basePath = BuildConfig.BASE_URL
        if (BuildConfig.DEBUG) {
            fuelManager.addResponseInterceptor { LogResponseInterceptor(it) }
        }
    }

    private fun setupKoin() {
        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(Level.DEBUG)
            }
            androidContext(this@App)
            modules(listOf(appModule, mainModule))
        }
    }
}