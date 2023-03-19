package com.example.weatherapp.project.repositories

import com.example.weatherapp.project.requests.Requests
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


@ActivityRetainedScoped
class Repository @Inject constructor(private val request: Requests){
    suspend fun getWeather(cityName:String)=request.getWeatherData(cityName)
}