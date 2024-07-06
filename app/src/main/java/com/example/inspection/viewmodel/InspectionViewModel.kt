package com.example.inspection.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.inspection.network.RetrofitInstance
import com.example.inspection.room.AppDatabase
import com.example.inspection.room.entity.Inspection
import com.example.inspection.room.repository.InspectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InspectionViewModel(application: Application) : AndroidViewModel(application) {

    private val inspectionDao = AppDatabase.getInstance(application).inspectionDao()
    private val inspectionRepository = InspectionRepository(inspectionDao)
    val inspectionListLiveData : MutableLiveData<List<Inspection>>
        get() {
            return inspectionRepository.inspectionListLiveData
        }

    fun getInspectionList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.getInspection()
                inspectionRepository.insertInspection(response.inspection)
                Log.d("2121","area name = ${response.inspection.area?.name}")
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun getInspectionListFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            inspectionRepository.getInspections()
        }
    }



/*    fun getInspectionListAPI() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getInspection()
                response.forEach { inspection ->
                    // Save the inspection data to the local database
                    inspection.name
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }*/

}