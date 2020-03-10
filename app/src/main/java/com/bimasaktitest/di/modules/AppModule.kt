package com.bimasaktitest.di.modules

import com.bimasaktitest.network.repositories.MainRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

    factory { MainRepository() }
    factory { com.bimasaktitest.local.repositories.MainRepository(androidApplication()) }

}