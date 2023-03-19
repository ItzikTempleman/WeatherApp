package com.example.weatherapp.project.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class WeatherResponse(
    @SerializedName("coord")
    val coordinates:Coordinates,
    val weather: List<Weather>,
    val main:Main,
    val visibility: Double,
    val wind: Wind,
    val clouds:Clouds,
    @SerializedName("sys")
    val moreInfo:Sys,
    val timezone: Int,
    val id:Long,
    @SerializedName("name")
    val cityName:String,
    @SerializedName("cod")
    val code:Int
)