package com.example.weatherapp.project.models

import com.google.gson.annotations.SerializedName


data class Weather(
    val location: Location,
    @SerializedName("current_observation")
    val observation: Observation,
    val forecasts: List<Forecast>,
)