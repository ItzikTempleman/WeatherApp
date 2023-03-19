package com.example.weatherapp.project.models

import com.google.gson.annotations.SerializedName

data class Sys (
val country:String,
@SerializedName("sunrise")
val sunRise:Double,
@SerializedName("sunset")
val sunSet:Double,
)