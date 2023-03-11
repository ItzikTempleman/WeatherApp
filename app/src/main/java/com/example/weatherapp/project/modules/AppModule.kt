package com.example.weatherapp.project.modules

import com.example.weatherapp.project.data.Requests
import com.example.weatherapp.project.data.WeatherRequestInterceptor
import com.example.weatherapp.project.main.Constants.BASE_URL
import com.example.weatherapp.project.main.Converters
import com.example.weatherapp.project.repositories.AppRepository
import com.example.weatherapp.project.repositories.AppRepositoryImp
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
class AppModule {
    private val typeConverter = Converters()

    @Provides
    @Singleton
    fun provideRepo(requests: Requests): AppRepository {
        return AppRepositoryImp(requests)
    }


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
}