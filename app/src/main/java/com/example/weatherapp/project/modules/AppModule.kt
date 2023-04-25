package com.example.weatherapp.project.modules


import com.example.weatherapp.project.requests.Requests
import com.example.weatherapp.project.requests.WeatherRequestInterceptor
import com.example.weatherapp.project.utils.Constants.BASE_URL


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
    private val typeConverter = Converters()
     */

    @Provides
    @Singleton
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


    @Singleton
    @Provides
    fun provideRequestService(retrofit: Retrofit): Requests =
        retrofit.create(Requests::class.java)

   /** @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, WEATHER_RESPONSE_DATABASE)
       addTypeConverter(typeConverter).
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): AppDao = appDatabase.getDao()
*/
}