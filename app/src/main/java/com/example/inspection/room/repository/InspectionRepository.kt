package com.example.inspection.room.repository

import androidx.lifecycle.MutableLiveData
import com.example.inspection.room.dao.InspectionDao
import com.example.inspection.room.entity.Inspection

class InspectionRepository(private val inspectionDao: InspectionDao) {

    private val mInspectionListLiveData = MutableLiveData<List<Inspection>>()

    val inspectionListLiveData : MutableLiveData<List<Inspection>>
        get() {
            return mInspectionListLiveData
        }

    fun getInspections(){
        mInspectionListLiveData.postValue(inspectionDao.getInspections())
    }

    fun insertInspection(inspection: Inspection) {
        return inspectionDao.insertInspection(inspection)
    }
}