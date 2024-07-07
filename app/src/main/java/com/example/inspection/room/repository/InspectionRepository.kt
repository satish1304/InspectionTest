package com.example.inspection.room.repository

import androidx.lifecycle.MutableLiveData
import com.example.inspection.model.Survey
import com.example.inspection.room.dao.InspectionDao
import com.example.inspection.room.entity.Inspection

class InspectionRepository(private val inspectionDao: InspectionDao) {

    private val mInspectionListLiveData = MutableLiveData<List<Inspection>>()

    val inspectionListLiveData : MutableLiveData<List<Inspection>>
        get() {
            return mInspectionListLiveData
        }

    fun getInspections(completed:Boolean){
        mInspectionListLiveData.postValue(inspectionDao.getInspections(completed))
    }

    fun insertInspection(inspection: Inspection) {
        return inspectionDao.insertInspection(inspection)
    }

    fun updateInspectionCategory(inspectionSurvey: Survey,score: Double, inspectionId: Int) {
        return inspectionDao.updateInspectionCategory(inspectionSurvey,score,inspectionId)
    }

    fun  updateInspectionCompleted(inspectionId: Int, inspectionDate: String){
        return inspectionDao.updateInspectionCompleted(inspectionId, inspectionDate)
    }
}