package com.example.weatherapp.project.requests

import com.example.weatherapp.project.main.Constants.API_HOST_NAME
import com.example.weatherapp.project.main.Constants.API_HOST_VALUE
import com.example.weatherapp.project.main.Constants.API_KEY_NAME
import com.example.weatherapp.project.main.Constants.API_KEY_VALUE
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class WeatherRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().newBuilder()
            .addHeader(API_KEY_NAME, API_KEY_VALUE)
            .addHeader(API_HOST_NAME, API_HOST_VALUE)
            .build()
        return chain.proceed(request)
    }
}

