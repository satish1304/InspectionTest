package com.example.inspection.room.converter

import androidx.room.TypeConverter
import com.example.inspection.model.Survey
import com.google.gson.Gson

class SurveyToGsonConverter {

    @TypeConverter
    fun convertSurveyToGson(survey: Survey): String {
        val gson = Gson()
        return gson.toJson(survey)
    }

    @TypeConverter
    fun convertGsonToSurvey(gsonString: String): Survey {
        val gson = Gson()
        return gson.fromJson(gsonString, Survey::class.java)
    }
}