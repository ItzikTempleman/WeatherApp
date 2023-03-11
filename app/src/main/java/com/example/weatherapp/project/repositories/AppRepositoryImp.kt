package com.example.weatherapp.project.repositories

import com.example.weatherapp.project.data.AppDao
import com.example.weatherapp.project.data.Requests
import com.example.weatherapp.project.models.Weather
import retrofit2.Response
import javax.inject.Inject

class AppRepositoryImp @Inject constructor(private val requests: Requests) : AppRepository {
    override suspend fun getWeather(unit: String, city: String) = requests.getWeatherData(unit, city)

}