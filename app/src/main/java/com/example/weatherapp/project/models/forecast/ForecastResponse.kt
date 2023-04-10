package com.example.weatherapp.project.models.forecast

import android.os.Parcelable
import com.example.weatherapp.project.models.current_weather.BaseModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastResponse(
    @SerializedName("list")
    val hourlyList :List<ForecastItem>,
    val coord:CoordinatesInForecast
): Parcelable, BaseModel()
