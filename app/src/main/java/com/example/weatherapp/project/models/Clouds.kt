package com.example.weatherapp.project.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Clouds(
        val all: Double
): Parcelable