package com.example.weatherapp.project.requests

import com.example.weatherapp.project.models.forecast.ForecastResponse
import com.example.weatherapp.project.models.location_images.LocationImageResponse
import com.example.weatherapp.project.models.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Requests {

    @GET("city/{cityName}")
    suspend fun getCityWeatherData(
        @Path("cityName") cityName: String,
    ): Response<WeatherResponse>


    @GET("city/fivedaysforcast/{lat}/{lon}")
    suspend fun getForecastData(
        @Path("lat") latitude: Double,
        @Path("lon") longitude: Double
    ): Response<ForecastResponse>

    @GET("{cityName}")
    suspend fun getCityImage(
        @Path("cityName") cityName: String,

    ): Response<LocationImageResponse>

}

