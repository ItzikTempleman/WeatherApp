package com.example.weatherapp.project.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Weather(
        @PrimaryKey
        val id: Int,
        val main: String,
        val description: String,
        val icon:String
        ): Parcelable
