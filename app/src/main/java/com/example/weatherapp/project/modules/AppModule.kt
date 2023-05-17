package com.example.weatherapp.project.modules


import com.example.weatherapp.project.requests.WeatherAndForecastService
import com.example.weatherapp.project.utils.Constants.BASE_URL
import com.example.weatherapp.project.utils.Constants.IMAGE_BASE_URL
import com.example.weatherapp.project.utils.ImageRequestInterceptor
import com.example.weatherapp.project.utils.WeatherRequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module

@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    @Named("weatherAndForecast")
    fun provideWeatherAndForecastService(): WeatherAndForecastService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(WeatherRequestInterceptor())
                    .build()
            )
            .build()
        return retrofit.create(WeatherAndForecastService::class.java)
    }



    @Provides
    @Singleton
    @Named("locationImages")
    fun provideLocationImageService(): WeatherAndForecastService {
        val retrofit = Retrofit.Builder()
            .baseUrl(IMAGE_BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(ImageRequestInterceptor())
                    .build()
            )
            .build()
        return retrofit.create(WeatherAndForecastService::class.java)
    }

}