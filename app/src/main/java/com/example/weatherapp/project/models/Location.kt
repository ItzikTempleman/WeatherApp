package com.example.weatherapp.project.models

import com.google.gson.annotations.SerializedName

data class Location(
val city:String,
@SerializedName("woeid")
val cityId:Long,
val country:String,
val lat:Double,
val long:Double,
@SerializedName("timezone_id")
val timeZoneId: Long
)
