package com.example.inspection.room.converter

import androidx.room.TypeConverter
import com.example.inspection.model.Categories
import com.google.gson.Gson

class CategoryToGsonConverter {

        @TypeConverter
        fun convertCategoryToGson(category: Categories): String {
            val gson = Gson()
            return gson.toJson(category)
        }

        @TypeConverter
        fun convertGsonToCategory(gsonString: String): Categories {
            val gson = Gson()
            return gson.fromJson(gsonString, Categories::class.java)
        }
}