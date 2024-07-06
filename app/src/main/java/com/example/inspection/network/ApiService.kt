package com.example.inspection.network


import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("api/random_inspection")
    suspend fun getInspection(): InspectionResponse

    @POST("api/register")
    suspend fun registerUser(@Body loginRequest: LoginRequest): Response<ResponseBody>

    @POST("api/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest) : Response<Unit>

}