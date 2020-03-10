package com.bimasaktitest.di.modules

import com.bimasaktitest.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val mainModule: Module = module {
    viewModel { MainViewModel() }
}