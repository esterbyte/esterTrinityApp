package com.ester.esterTrinityApp

import android.app.Application
import com.ester.esterTrinityApp.di.modules.AppModule
import com.ester.esterTrinityApp.di.modules.NetworkModule
import com.ester.esterTrinityApp.di.modules.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TrinityApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@TrinityApplication)
            modules(listOf(AppModule, NetworkModule, dbModule))
        }
    }
}