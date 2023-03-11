package com.example.weatherapp.project.repositories

interface AppRepository {
    suspend fun getWeather(unit:String, city:String)
}