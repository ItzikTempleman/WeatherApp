package com.example.weatherapp.project.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Coordinates (
    val lon:Double,
    val lat:Double
        ): Parcelable
