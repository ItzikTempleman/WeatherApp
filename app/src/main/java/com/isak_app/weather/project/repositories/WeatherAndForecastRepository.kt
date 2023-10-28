package com.isak_app.weather.project.repositories

import com.isak_app.weather.project.models.forecast.ForecastResponse
import com.isak_app.weather.project.models.location_images.ImageResponse
import com.isak_app.weather.project.models.weather.WeatherResponse
import retrofit2.Response


interface WeatherAndForecastRepository {

    suspend fun getWeather(cityName: String): Response<WeatherResponse>
    suspend fun getForecast(lat:Double, lon:Double): Response<ForecastResponse>
    suspend fun getLocationImage(cityName:String, clientId:String): Response<ImageResponse>
}