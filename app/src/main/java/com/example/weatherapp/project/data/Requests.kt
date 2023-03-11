package com.example.weatherapp.project.data

import com.example.weatherapp.project.models.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Requests {

    @GET("weather")
    suspend fun getWeatherData(
        @Query("u") units: String,
        @Query("location") cityName: String,
    ): Response<Weather>
}

