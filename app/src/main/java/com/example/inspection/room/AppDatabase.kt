package com.example.inspection.room

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.inspection.room.converter.AreaToGsonConverter
import com.example.inspection.room.converter.CategoryToGsonConverter
import com.example.inspection.room.converter.InspectionToGsonConverter
import com.example.inspection.room.converter.InspectionTypeToGsonConverter
import com.example.inspection.room.converter.QuestionsToGsonConverter
import com.example.inspection.room.converter.SurveyToGsonConverter
import com.example.inspection.room.dao.InspectionDao
import com.example.inspection.room.dao.UserDao
import com.example.inspection.room.entity.Inspection
import com.example.inspection.room.entity.User


@Database(version = 1, entities = [Inspection::class, User::class], exportSchema = false)
@TypeConverters(InspectionToGsonConverter::class, AreaToGsonConverter::class,CategoryToGsonConverter::class,QuestionsToGsonConverter::class,SurveyToGsonConverter::class,InspectionTypeToGsonConverter::class)

abstract class AppDatabase : RoomDatabase() {

abstract fun userDao(): UserDao
abstract fun inspectionDao(): InspectionDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}