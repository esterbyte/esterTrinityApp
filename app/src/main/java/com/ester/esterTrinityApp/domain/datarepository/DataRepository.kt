package com.ester.esterTrinityApp.domain.datarepository

import com.ester.esterTrinityApp.data.model.Data


interface DataRepository {
    suspend fun saveLocalData(localData: Data): Long
    suspend fun getLocalData(): Data
    suspend fun getMarsData(): Data

}