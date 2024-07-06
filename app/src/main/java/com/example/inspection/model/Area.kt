package com.example.inspection.model

import com.google.gson.annotations.SerializedName


data class Area (

  @SerializedName("id") var id : Int? = null,
  @SerializedName("name") var name : String? = null

)