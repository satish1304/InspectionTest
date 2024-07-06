package com.example.inspection.room.converter

import androidx.room.TypeConverter
import com.example.inspection.model.InspectionType
import com.google.gson.Gson

class InspectionTypeToGsonConverter {

    @TypeConverter
    fun convertInspectionTypeToGson(inspectionType: InspectionType): String {
        val gson = Gson()
        return gson.toJson(inspectionType)
    }

    @TypeConverter
    fun convertGsonToInspectionType(gsonString: String): InspectionType {
        val gson = Gson()
        return gson.fromJson(gsonString, InspectionType::class.java)
    }
}