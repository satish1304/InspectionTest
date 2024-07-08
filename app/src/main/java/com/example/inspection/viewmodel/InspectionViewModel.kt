package com.example.inspection.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.inspection.model.Categories
import com.example.inspection.model.Survey
import com.example.inspection.network.InspectionSubmitRequest
import com.example.inspection.network.RetrofitInstance
import com.example.inspection.room.AppDatabase
import com.example.inspection.room.entity.Inspection
import com.example.inspection.room.repository.InspectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InspectionViewModel(application: Application) : AndroidViewModel(application) {

    private val inspectionDao = AppDatabase.getInstance(application).inspectionDao()
    private val inspectionRepository = InspectionRepository(inspectionDao)
    val submitInspectionResponseCd: LiveData<Int>
        get(){
            return inspectionRepository.submitInspectionResponse
        }

    val inspectionListLiveData : MutableLiveData<List<Inspection>>
        get() {
            return inspectionRepository.inspectionListLiveData
        }


    fun startInspection() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.startInspection()
                inspectionRepository.insertInspection(response.inspection)
                val inspectionList = inspectionListLiveData.value as ArrayList
                inspectionList.add(response.inspection)
                inspectionListLiveData.postValue(inspectionList)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun submitInspection(inspectionSubmitRequest : InspectionSubmitRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            inspectionRepository.submitInspection(inspectionSubmitRequest)
        }
    }

    fun getInspectionListFromDB(completed:Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            inspectionRepository.getInspections(completed)
        }
    }

    fun updateInspectionCategory(inspectionSurvey: Survey, score: Double, inspectionId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            inspectionRepository.updateInspectionCategory(inspectionSurvey,score,inspectionId)
        }
    }

    fun updateInspectionCompleted( inspectionId: Int, inspectionDate: String) {
        viewModelScope.launch(Dispatchers.IO) {
            inspectionRepository.updateInspectionCompleted(inspectionId, inspectionDate)
        }
    }

    fun calculateScore(category: Categories) : Double{
        var score = 0.0
        category.questions.forEach { question ->
            val selectedAnswerId = question.selectedAnswerChoiceId
            question.answerChoices.forEach { answerChoice ->
                if(answerChoice.id.toString() == selectedAnswerId){
                    score = answerChoice.score!! + answerChoice.score!!
                }
            }
        }
        return score
    }

}