package com.example.weatherapp.project.models

import com.google.gson.annotations.SerializedName

data class Condition(
    val temperature: Int,
    @SerializedName("text")
    val weatherDescription: String,
    val code: Int,
)
