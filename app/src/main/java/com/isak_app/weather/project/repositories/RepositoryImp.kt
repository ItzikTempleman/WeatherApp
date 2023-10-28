package com.isak_app.weather.project.repositories

import com.isak_app.weather.project.models.forecast.ForecastResponse
import com.isak_app.weather.project.models.location_images.ImageResponse
import com.isak_app.weather.project.models.weather.WeatherResponse
import com.isak_app.weather.project.requests.WeatherAndForecastService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


class RepositoryImp @Inject constructor(


    @Singleton
    @Named("weatherAndForecast") private val weatherAndForecastRequest: WeatherAndForecastService,

    @Singleton
    @Named("locationImages") private val locationImageRequest: WeatherAndForecastService

) : WeatherAndForecastRepository {

    override suspend fun getWeather(cityName: String): Response<WeatherResponse> =
        weatherAndForecastRequest.getCityWeatherData(cityName)

    override suspend fun getForecast(lat: Double, lon: Double): Response<ForecastResponse> =
        weatherAndForecastRequest.getForecastData(lat, lon)

    override suspend fun getLocationImage(
        cityName: String,
        clientId: String
    ): Response<ImageResponse> =
        locationImageRequest.getLocationImage(cityName, clientId)
}

