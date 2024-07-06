package com.example.inspection.model

import com.google.gson.annotations.SerializedName


data class InspectionType (

  @SerializedName("access") var access : String? = null,
  @SerializedName("id") var id : Int?    = null,
  @SerializedName("name") var name : String? = null

)