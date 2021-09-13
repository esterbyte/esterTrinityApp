package com.ester.esterTrinityApp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "data_table")
data class Data(
    val photos: ArrayList<Photo> = ArrayList(),
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) : Parcelable

@Parcelize
data class Photo(
    val img_src: String = "",
    val earth_date: String = "",
    val rover: Rover? = null,
    val camera: Camera? = null
) : Parcelable

@Parcelize
data class Rover(
    val name: String = ""
) : Parcelable

@Parcelize
data class Camera(
    val name: String = ""
) : Parcelable