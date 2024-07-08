package com.example.inspection.network


import com.example.inspection.room.entity.Inspection
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("api/inspections/start")
    suspend fun startInspection(): InspectionResponse

    @POST("api/register")
    suspend fun registerUser(@Body loginRequest: LoginRequest): Response<ResponseBody>

    @POST("api/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest) : Response<ResponseBody>

    @POST("api/inspections/submit")
    suspend fun submitInspection(@Body inspectionSubmitRequest : InspectionSubmitRequest): Response<ResponseBody>
}