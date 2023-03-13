package com.example.weatherapp.project.modules

import com.example.weatherapp.project.requests.Requests
import com.example.weatherapp.project.requests.WeatherRequestInterceptor
import com.example.weatherapp.project.main.Constants.BASE_URL
import com.example.weatherapp.project.main.Converters
import com.example.weatherapp.project.repositories.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val typeConverter = Converters()


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(WeatherRequestInterceptor())
                    .build()
            )
            .build()


    @Singleton
    @Provides
    fun provideRequestService(retrofit: Retrofit): Requests =
        retrofit.create(Requests::class.java)
}