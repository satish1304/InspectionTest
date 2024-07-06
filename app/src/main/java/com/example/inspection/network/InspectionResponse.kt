package com.example.inspection.network


import com.example.inspection.room.entity.Inspection
import com.google.gson.annotations.SerializedName

class InspectionResponse {
    @SerializedName("inspection" )
    lateinit var inspection : Inspection// = Inspection()
}