package com.example.inspection.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Questions (

  @SerializedName("answerChoices") var answerChoices : ArrayList<AnswerChoices> = arrayListOf(),
  @SerializedName("id") var id : Int? = null,
  @SerializedName("name") var name : String? = null,
  @SerializedName("selectedAnswerChoiceId") var selectedAnswerChoiceId : String? = null

) : Parcelable