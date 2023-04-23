package com.example.weatherapp.project.main

import com.example.weatherapp.project.models.current_weather.*
import com.example.weatherapp.project.models.forecast.City
import com.example.weatherapp.project.models.forecast.CoordinatesInForecast
import com.example.weatherapp.project.models.forecast.ForecastResponse

fun getEmptyData(): WeatherResponse {
    return WeatherResponse(
        coordinates = Coordinates(
            0.0, 0.0
        ),
        weather = emptyList(),
        main = Main(
            temp = 0.0,
            feelsLike = 0.0,
            tempMin = 0.0,
            tempMax = 0.0,
            pressure = 0,
            humidity = 0
        ),
        visibility = 0.0,
        wind = Wind(
            speed = 0.0,
            deg = 0,
        ),
        clouds = Clouds(
            all = 0.0
        ),
        moreInfo = Sys(
            country = "",
            sunRise = 0.0,
            sunSet = 0.0
        ),
        timezone = 0,
        cityName = "",
        code = 0
    )
}

fun getForecastEmptyData(): ForecastResponse {
    return ForecastResponse(
        hourlyList = emptyList(),
        city = City(
            coord = CoordinatesInForecast(
                0.0, 0.0
            )
        )
    )
}