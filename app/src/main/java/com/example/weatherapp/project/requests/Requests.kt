package com.example.weatherapp.project.requests

import com.example.weatherapp.project.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Requests {

    @GET("city/{cityName}")
    suspend fun getCityWeatherData(
        @Path("cityName") cityName: String,
    ): Response<WeatherResponse>
}

