package com.example.weatherapp.project.models.current_weather

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.project.main.Constants
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Weather(@PrimaryKey val id: Int, val main: String, val description: String, val icon: String) :
    Parcelable {
    fun getImage() = Constants.WEATHER_ICON_URL + icon + Constants.WEATHER_ICON_URL_ENDING
}
