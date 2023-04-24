package com.example.weatherapp.project.models.forecast

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoordinatesInForecast (
    val lat: Double,
    val lon: Double
): Parcelable
