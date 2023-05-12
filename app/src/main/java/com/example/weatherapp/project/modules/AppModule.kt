package com.example.weatherapp.project.modules


import com.example.weatherapp.project.requests.ImageRequestInterceptor
import com.example.weatherapp.project.requests.Requests
import com.example.weatherapp.project.requests.WeatherRequestInterceptor
import com.example.weatherapp.project.utils.Constants.BASE_URL
import com.example.weatherapp.project.utils.Constants.IMAGE_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module

@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideWeatherAndForecastRequestService(@Named("weatherAndForecast") retrofit: Retrofit): Requests =
        retrofit.create(Requests::class.java)


    @Provides
    fun provideLocationImageRequestService(@Named("locationImage") retrofit: Retrofit): Requests =
        retrofit.create(Requests::class.java)


    @Provides
    @Singleton
    @Named("weatherAndForecast")
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(WeatherRequestInterceptor())
                    .addInterceptor(interceptor)
                    .build()
            )
            .build()
    }


    @Provides

    @Named("locationImage")
    fun provideImageRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(IMAGE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(ImageRequestInterceptor())
                    .build()
            )
            .build()
    }




}