package com.example.weatherapp.project.models.forecast

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MainInForecast (
        val temp: Double
        ): Parcelable
