package com.example.weatherapp.project.repositories

import com.example.weatherapp.project.models.forecast.ForecastResponse
import com.example.weatherapp.project.models.location_images.LocationImageResponse
import com.example.weatherapp.project.models.unsplash_location_image.UnsplashImageResponse
import com.example.weatherapp.project.models.weather.WeatherResponse
import com.example.weatherapp.project.requests.WeatherAndForecastService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


class RepositoryImp @Inject constructor(


    @Singleton
    @Named("weatherAndForecast") private val weatherAndForecastRequest: WeatherAndForecastService,

    @Singleton
    @Named("locationImages") private val locationImageRequest: WeatherAndForecastService,

    @Singleton
    @Named("unsplashLocationImages") private val unsplashLocationImageRequest: WeatherAndForecastService

) : WeatherAndForecastRepository {

    override suspend fun getWeather(cityName: String): Response<WeatherResponse> =
        weatherAndForecastRequest.getCityWeatherData(cityName)

    override suspend fun getForecast(lat: Double, lon: Double): Response<ForecastResponse> =
        weatherAndForecastRequest.getForecastData(lat, lon)

    override suspend fun getLocationImage(locationImage: String): Response<LocationImageResponse> =
        locationImageRequest.getCityImage(locationImage)

    override suspend fun getLocationImageFromUnsplashApi(
        cityName: String,
        clientId: String
    ): Response<UnsplashImageResponse> =
        unsplashLocationImageRequest.getLocationImageFromUnsplash(cityName, clientId)
}

