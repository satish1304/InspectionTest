package com.example.inspection.room.converter

import androidx.room.TypeConverter
import com.example.inspection.model.Questions
import com.google.gson.Gson

class QuestionsToGsonConverter {

    @TypeConverter
    fun convertQuestionsToGson(questions: Questions): String {
        val gson = Gson()
        return gson.toJson(questions)
    }

    @TypeConverter
    fun convertGsonToQuestions(gsonString: String): Questions {
        val gson = Gson()
        return gson.fromJson(gsonString, Questions::class.java)
    }
}