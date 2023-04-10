package com.example.weatherapp.project.models.forecast

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherInForecast(
val main:String,
val description:String,
val icon:String
): Parcelable
