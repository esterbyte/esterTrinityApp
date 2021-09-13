package com.ester.esterTrinityApp.util

import androidx.room.TypeConverter
import com.ester.esterTrinityApp.data.model.Camera
import com.ester.esterTrinityApp.data.model.Data
import com.ester.esterTrinityApp.data.model.Photo
import com.ester.esterTrinityApp.data.model.Rover
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    class DataConverter {
        @TypeConverter
        fun toObject(json: String): Data {
            val type = object : TypeToken<Rover>() {}.type
            return Gson().fromJson(json, type)
        }

        @TypeConverter
        fun toJson(obj: Data): String {
            val type = object : TypeToken<Data>() {}.type
            return Gson().toJson(obj, type)
        }
    }

    class PhotoConverter {

        @TypeConverter
        fun toObject(value: ArrayList<Photo>): String {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Photo>>() {}.type
            return gson.toJson(value, type)
        }

        @TypeConverter
        fun toJson(value: String): ArrayList<Photo> {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Photo>>() {}.type
            return gson.fromJson(value, type)
        }
    }

    class RoverConverters {
        @TypeConverter
        fun toObject(json: String): Rover {
            val type = object : TypeToken<Rover>() {}.type
            return Gson().fromJson(json, type)
        }

        @TypeConverter
        fun toJson(obj: Rover): String {
            val type = object : TypeToken<Rover>() {}.type
            return Gson().toJson(obj, type)
        }
    }

    class CameraConverter {
        @TypeConverter
        fun toObject(json: String): Camera {
            val type = object : TypeToken<Camera>() {}.type
            return Gson().fromJson(json, type)
        }

        @TypeConverter
        fun toJson(obj: Camera): String {
            val type = object : TypeToken<Camera>() {}.type
            return Gson().toJson(obj, type)
        }
    }


}