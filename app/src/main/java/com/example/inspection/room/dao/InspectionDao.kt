package com.example.inspection.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.inspection.room.entity.Inspection

@Dao
interface InspectionDao {

    @Query("SELECT * FROM inspection")
    fun getInspections() : List<Inspection>

    @Insert
    fun insertInspection(vararg inspection: Inspection)
}