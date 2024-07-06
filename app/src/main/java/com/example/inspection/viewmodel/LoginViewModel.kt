package com.example.inspection.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.inspection.network.LoginRequest
import com.example.inspection.network.RetrofitInstance
import com.example.inspection.room.AppDatabase
import com.example.inspection.room.entity.User
import com.example.inspection.room.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel(application:Application) : AndroidViewModel(application) {
    private val userDao = AppDatabase.getInstance(application).userDao()
    private val  loginRepository : LoginRepository = LoginRepository(userDao)
    val loginResponseCd: LiveData<Int>
        get() = loginRepository.loginResponse

     fun validateUser(email: String, password: String) : Boolean{
        var isValidUser = false
         viewModelScope.launch {
             isValidUser = loginRepository.validateUser(email, password)
         }

        return isValidUser
     }

     fun insert(user: User) = viewModelScope.launch(Dispatchers.IO){
         loginRepository.insert(user)
     }

    fun callLoginAPI(email:String, password:String){
        viewModelScope.launch {
          try {
              loginRepository.loginUser(email, password)
             /* RetrofitInstance.api.registerUser(loginRequest).enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                    // Handle the response
                    Log.d("LoginViewModel", "Response: ${response.code()}")
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    // Handle the failure
                    Log.d("LoginViewModel", "Error: ${t.message}")
                }
            })*/

          }catch (e: Exception){
                e.printStackTrace()
          }

        }

    }

}