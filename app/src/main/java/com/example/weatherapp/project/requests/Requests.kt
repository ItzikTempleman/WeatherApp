package com.example.weatherapp.project.requests

import com.example.weatherapp.project.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Requests {

    @GET("weather?")
    suspend fun getWeatherData(
        @Query("city") cityName: String
    ): Response<WeatherResponse>
}

