package com.example.inspection.model

import com.google.gson.annotations.SerializedName


data class Survey (

  @SerializedName("categories" ) var categories : ArrayList<Categories> = arrayListOf()

)