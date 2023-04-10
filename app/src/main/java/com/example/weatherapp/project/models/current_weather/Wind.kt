package com.example.weatherapp.project.models.current_weather

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Wind (
val speed:Double,
val deg:Int
): Parcelable
