package com.example.inspection.room.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.inspection.network.LoginRequest
import com.example.inspection.network.RetrofitInstance
import com.example.inspection.room.dao.UserDao
import com.example.inspection.room.entity.User
import com.example.inspection.utils.AppUtils

class LoginRepository(private val userDao: UserDao) {
    private val mLoginResponse = MutableLiveData<Int>()
    val loginResponse: LiveData<Int>
        get() {
            return mLoginResponse
        }


    fun insert(user: User) {
        userDao.insertUser(user)
    }


    suspend fun loginUser(email: String, password: String) {
        // Call the API and update the response
        try {
          val result = RetrofitInstance.api.loginUser(LoginRequest(email, password))
          if(result.code()!=0){
              mLoginResponse.postValue(result.code())
          }
        }catch (e: Exception){
            e.printStackTrace()
            mLoginResponse.postValue(0)
        }
    }

    suspend fun registerUser(email: String, password: String) {
        // Call the API and update the response
        try {
            val result = RetrofitInstance.api.registerUser(LoginRequest(email, password))
            if(result.code()!=0){
                mLoginResponse.postValue(result.code())
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}