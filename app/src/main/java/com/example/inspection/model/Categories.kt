package com.example.inspection.model

import com.google.gson.annotations.SerializedName


data class Categories (

  @SerializedName("id") var id:Int? = null,
  @SerializedName("name") var name : String? = null,
  @SerializedName("questions") var questions : ArrayList<Questions> = arrayListOf()

)