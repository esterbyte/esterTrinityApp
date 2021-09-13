package com.ester.esterTrinityApp.di.dependencyInjection

import androidx.room.Room
import com.ester.esterTrinityApp.data.source.Database
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            get(),
            Database::class.java,
            "todo_database"
        ).fallbackToDestructiveMigration().build()
    }
    factory { get<Database>().trinityDao() }
}