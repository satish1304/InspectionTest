package com.example.inspection.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.inspection.model.Survey
import com.example.inspection.room.entity.Inspection

@Dao
interface InspectionDao {

    @Query("SELECT * FROM inspection WHERE completed = :completed")
    fun getInspections(completed:Boolean) : List<Inspection>

    @Insert
    fun insertInspection(vararg inspection: Inspection)

    @Query("UPDATE inspection SET survey = :inspectionSurvey,score = COALESCE(score , 0.0) + :inspectionScore WHERE id = :inspectionId")
    fun updateInspectionCategory(inspectionSurvey: Survey,inspectionScore: Double,inspectionId: Int)

    @Query("UPDATE inspection SET completed = 1,inspectionDate= :inspectionDate WHERE id = :inspectionId")
    fun updateInspectionCompleted(inspectionId: Int, inspectionDate: String)

}