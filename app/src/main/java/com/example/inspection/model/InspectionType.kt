package com.example.inspection.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class InspectionType (

  @SerializedName("access") var access : String? = null,
  @SerializedName("id") var id : Int?    = null,
  @SerializedName("name") var name : String? = null

) : Parcelable