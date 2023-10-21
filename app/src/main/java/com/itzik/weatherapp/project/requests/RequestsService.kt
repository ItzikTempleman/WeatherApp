package com.itzik.weatherapp.project.requests

import com.itzik.weatherapp.project.models.forecast.ForecastResponse
import com.itzik.weatherapp.project.models.location_images.ImageResponse
import com.itzik.weatherapp.project.models.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherAndForecastService {

    @GET("city/{cityName}")
    suspend fun getCityWeatherData(
        @Path("cityName") cityName: String,
    ): Response<WeatherResponse>

    @GET("city/fivedaysforcast/{lat}/{lon}")
    suspend fun getForecastData(
        @Path("lat") latitude: Double,
        @Path("lon") longitude: Double
    ): Response<ForecastResponse>


    @GET("search/photos?")
    suspend fun getLocationImage(
        @Query("query") cityName: String,
        @Query("client_id") clientId: String
    ): Response<ImageResponse>
}

