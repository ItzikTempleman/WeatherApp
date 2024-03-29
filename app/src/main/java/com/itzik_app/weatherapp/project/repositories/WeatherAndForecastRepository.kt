package com.itzik_app.weatherapp.project.repositories

import com.itzik_app.weatherapp.project.models.forecast.ForecastResponse
import com.itzik_app.weatherapp.project.models.location_images.ImageResponse
import com.itzik_app.weatherapp.project.models.weather.WeatherResponse
import retrofit2.Response


interface WeatherAndForecastRepository {

    suspend fun getWeather(cityName: String): Response<WeatherResponse>
    suspend fun getForecast(lat:Double, lon:Double): Response<ForecastResponse>
    suspend fun getLocationImage(cityName:String, clientId:String): Response<ImageResponse>
}