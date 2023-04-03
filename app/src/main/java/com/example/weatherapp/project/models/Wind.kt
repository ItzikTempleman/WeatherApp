package com.example.weatherapp.project.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Wind (
val speed:Double,
val deg:Int
): Parcelable
