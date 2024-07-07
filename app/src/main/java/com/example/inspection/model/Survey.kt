package com.example.inspection.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Survey (

  @SerializedName("categories" ) var categories : ArrayList<Categories> = arrayListOf()

): Parcelable