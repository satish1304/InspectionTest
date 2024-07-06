package com.example.inspection.model

import com.google.gson.annotations.SerializedName


data class AnswerChoices (

  @SerializedName("id") var id    : Int?    = null,
  @SerializedName("name") var name  : String? = null,
  @SerializedName("score") var score : Double? = null

)