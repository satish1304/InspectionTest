package com.example.inspection.room.converter

import androidx.room.TypeConverter
import com.example.inspection.room.entity.Inspection
import com.google.gson.Gson

class InspectionToGsonConverter {

    @TypeConverter
    fun convertInspectionToGson(inspection: Inspection): String {
        val gson = Gson()
        return gson.toJson(inspection)
    }

    @TypeConverter
    fun convertGsonToInspection(gsonString: String): Inspection {
        val gson = Gson()
        return gson.fromJson(gsonString, Inspection::class.java)
    }
}