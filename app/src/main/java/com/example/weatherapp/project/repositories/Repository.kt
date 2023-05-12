package com.example.weatherapp.project.repositories

import com.example.weatherapp.project.requests.Requests
import javax.inject.Inject


class Repository @Inject constructor(private val request: Requests){

    suspend fun getWeather(cityName: String) = request.getCityWeatherData(cityName)

    suspend fun getForecast(lat:Double, lon:Double)=request.getForecastData(lat,lon)

    suspend fun getLocationImage(locationImage:String)=request.getCityImage(locationImage)

}