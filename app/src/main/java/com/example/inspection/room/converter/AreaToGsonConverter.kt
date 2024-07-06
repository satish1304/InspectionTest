package com.example.inspection.room.converter

import androidx.room.TypeConverter
import com.example.inspection.model.Area
import com.google.gson.Gson

class AreaToGsonConverter {

    @TypeConverter
    fun convertAreaToGson(area: Area): String {
        val gson = Gson()
        return gson.toJson(area)
    }

    @TypeConverter
    fun convertGsonToArea(gsonString: String): Area {
        val gson = Gson()
        return gson.fromJson(gsonString, Area::class.java)
    }
}