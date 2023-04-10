package com.example.weatherapp.project.models.forecast

import android.os.Parcelable
import androidx.core.app.NotificationCompat.Action.SemanticAction
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastItem(
    val main: MainInForecast,
    @SerializedName("weather")
    val weatherInForecast: List<WeatherInForecast>,
    @SerializedName("dt_txt")
    val exactTime: String,
    ): Parcelable