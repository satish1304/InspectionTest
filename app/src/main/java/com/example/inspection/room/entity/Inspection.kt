package com.example.inspection.room.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.inspection.model.Area
import com.example.inspection.model.InspectionType
import com.example.inspection.model.Survey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "inspection")
data class Inspection(
    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:Int,
    @ColumnInfo(name = "score")
    val score: Double?,
    @SerializedName("inspectionType" )
    @ColumnInfo(name = "inspectionType")
    val inspectionType : InspectionType,
    @ColumnInfo(name = "inspectionDate")
    val inspectionDate:String?,
    @ColumnInfo(name = "completed")
    val completed: Boolean,
    @SerializedName("area")
    @ColumnInfo(name = "area")
    val area : Area,
    @ColumnInfo(name = "survey")
    @SerializedName("survey")
    var survey : Survey
) : Parcelable