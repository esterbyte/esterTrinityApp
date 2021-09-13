package com.ester.esterTrinityApp.di.modules

import com.ester.esterTrinityApp.presentation.RegisterViewModel
import com.ester.esterTrinityApp.presentation.HomeAdapter
import com.ester.esterTrinityApp.presentation.HomeViewModel
import com.ester.esterTrinityApp.presentation.LoginScreenViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    single { createTrinityRepository(get(), get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { LoginScreenViewModel() }
    viewModel { RegisterViewModel() }

    factory { HomeAdapter() }

}