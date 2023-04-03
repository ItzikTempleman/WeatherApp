package com.example.weatherapp.project.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.project.main.Constants.WEATHER_RESPONSE_TABLE_TABLE
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = WEATHER_RESPONSE_TABLE_TABLE)
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
    @SerializedName("name")
    val cityName:String,
    @SerializedName("cod")
    val code:Int
) :Parcelable, BaseModel()