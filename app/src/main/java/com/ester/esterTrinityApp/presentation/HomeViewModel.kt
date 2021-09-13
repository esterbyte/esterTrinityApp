package com.ester.esterTrinityApp.presentation

import androidx.lifecycle.MutableLiveData
import com.ester.esterTrinityApp.data.model.Data
import com.ester.esterTrinityApp.data.model.Photo
import com.ester.esterTrinityApp.domain.datarepository.DataRepository
import kotlinx.coroutines.launch


class HomeViewModel(private val dataRepository: DataRepository) : BaseViewModel() {

    private var listPhotosHelper: MutableList<Photo> = mutableListOf()
    var listPhotos: MutableLiveData<MutableList<Photo>> = MutableLiveData()
    var errorRoom = MutableLiveData<Unit>()

    fun getData() {
        scope.launch {
            try {
                val data = dataRepository.getMarsData()
                listPhotosHelper.addAll(data.photos)
                listPhotos.value = listPhotosHelper
                saveDataLocal(data)
            } catch (e: Exception) {
                e.printStackTrace()
                getLocalData()
            }
        }
    }

    private fun saveDataLocal(localData: Data) {
        scope.launch {
            try {
                dataRepository.saveLocalData(localData)
            } catch (e: Exception) {
                errorRoom.value = Unit
                e.printStackTrace()
            }
        }
    }

    private fun getLocalData() {
        scope.launch {
            try {
                val data: Data = dataRepository.getLocalData()
                listPhotosHelper.addAll(data.photos)
                listPhotos.value = listPhotosHelper
            } catch (e: Exception) {
                errorRoom.value = Unit
                e.printStackTrace()
            }
        }
    }
}