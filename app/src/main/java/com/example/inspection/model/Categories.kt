package com.example.inspection.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Categories (

  @SerializedName("id") var id:Int? = null,
  @SerializedName("name") var name : String? = null,
  @SerializedName("questions") var questions : ArrayList<Questions> = arrayListOf()

) : Parcelable