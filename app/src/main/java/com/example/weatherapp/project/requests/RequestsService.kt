package com.example.weatherapp.project.requests

import com.example.weatherapp.project.models.forecast.ForecastResponse
import com.example.weatherapp.project.models.location_images.LocationImageResponse
import com.example.weatherapp.project.models.unsplash_location_image.UnsplashImageResponse
import com.example.weatherapp.project.models.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
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

    @GET("locations/v2/auto-complete?")
    suspend fun getCityImage(@Query("query") cityName: String): Response<LocationImageResponse>

    @GET("search/photos?")
    suspend fun getLocationImageFromUnsplash(
        @Query("query") cityName: String,
        @Header("client_id") clientId: String
    ): Response<UnsplashImageResponse>
}