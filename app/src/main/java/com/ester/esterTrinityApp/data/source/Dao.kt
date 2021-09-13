package com.ester.esterTrinityApp.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ester.esterTrinityApp.data.model.Data



@Dao
interface Dao {
    @Insert
    suspend fun saveLocalPhoto(localData: Data): Long

    @Query("SELECT * FROM data_table")
    suspend fun getLocalPhoto(): Data

}