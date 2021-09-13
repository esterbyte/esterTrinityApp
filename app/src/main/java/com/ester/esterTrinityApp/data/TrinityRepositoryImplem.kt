package com.ester.esterTrinityApp.data

import com.ester.esterTrinityApp.data.source.Dao
import com.ester.esterTrinityApp.data.model.Data
import com.ester.esterTrinityApp.data.source.ApiService
import com.ester.esterTrinityApp.domain.datarepository.DataRepository

class TrinityRepositoryImplem(
    private val trinityDao: Dao,
    private val nasaService: ApiService
) : DataRepository {
    override suspend fun saveLocalData(localData: Data): Long {
        return trinityDao.saveLocalPhoto(localData)
    }

    override suspend fun getLocalData(): Data {
        return trinityDao.getLocalPhoto()
    }

    override suspend fun getMarsData(): Data {
        return nasaService.getMarsData()
    }
}