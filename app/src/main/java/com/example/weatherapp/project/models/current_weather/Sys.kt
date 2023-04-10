package com.example.weatherapp.project.models.current_weather

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Sys (
val country:String,
@SerializedName("sunrise")
val sunRise:Double,
@SerializedName("sunset")
val sunSet:Double,
): Parcelable