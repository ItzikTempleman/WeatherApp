package com.itzik.weatherapp.project.utils

import com.itzik.weatherapp.BuildConfig
import com.itzik.weatherapp.project.utils.Constants.API_HOST_NAME
import com.itzik.weatherapp.project.utils.Constants.API_HOST_VALUE
import com.itzik.weatherapp.project.utils.Constants.API_KEY_NAME
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class WeatherRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().newBuilder()
            .addHeader(API_KEY_NAME, BuildConfig.API_KEY)
            .addHeader(API_HOST_NAME, API_HOST_VALUE)
            .build()
        return chain.proceed(request)
    }
}
