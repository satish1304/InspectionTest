package com.example.inspection.room.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.inspection.model.Survey
import com.example.inspection.network.InspectionSubmitRequest
import com.example.inspection.network.RetrofitInstance
import com.example.inspection.room.dao.InspectionDao
import com.example.inspection.room.entity.Inspection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InspectionRepository(private val inspectionDao: InspectionDao) {

    private val mInspectionListLiveData = MutableLiveData<List<Inspection>>()

    private val mSubmitInspectionResponse = MutableLiveData<Int>()
    val submitInspectionResponse: LiveData<Int>
        get() {
            return mSubmitInspectionResponse
        }


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

  suspend  fun submitInspection(inspectionSubmitRequest : InspectionSubmitRequest) {
      try {
          val response = RetrofitInstance.api.submitInspection(inspectionSubmitRequest)
          Log.d("InspectionRepository", "submitInspection: ${response.code()}")
          if(response.code()!=0){
              mSubmitInspectionResponse.postValue(response.code())
          }
      }catch (e: Exception){
          e.printStackTrace()
          mSubmitInspectionResponse.postValue(0)
      }

    }


}