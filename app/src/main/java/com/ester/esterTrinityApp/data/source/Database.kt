package com.ester.esterTrinityApp.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ester.esterTrinityApp.data.model.Data
import com.ester.esterTrinityApp.util.Converters

@Database(entities = [Data::class], version = 1, exportSchema = true)
@TypeConverters(
    Converters.CameraConverter::class,
    Converters.RoverConverters::class,
    Converters.DataConverter::class,
    Converters.PhotoConverter::class
)

abstract class Database: RoomDatabase() {
    abstract fun trinityDao(): Dao
}