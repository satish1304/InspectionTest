package com.example.inspection.model

import com.google.gson.annotations.SerializedName


data class Questions (

  @SerializedName("answerChoices") var answerChoices : ArrayList<AnswerChoices> = arrayListOf(),
  @SerializedName("id") var id : Int? = null,
  @SerializedName("name") var name : String? = null,
  @SerializedName("selectedAnswerChoiceId") var selectedAnswerChoiceId : String? = null

)