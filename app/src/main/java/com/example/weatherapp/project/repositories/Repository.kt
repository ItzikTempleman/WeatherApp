package com.example.weatherapp.project.repositories

import com.example.weatherapp.project.models.WeatherResponse
import com.example.weatherapp.project.requests.BaseApiResponse
import com.example.weatherapp.project.requests.NetworkResult
import com.example.weatherapp.project.requests.Requests
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(private val requests: Requests) : BaseApiResponse() {
    suspend fun getWeatherData(unit: String, city: String): Flow<NetworkResult<WeatherResponse>> {
        return flow {
            emit(safeApiCall {
                requests.getWeatherData(unit, city)
                }
            )
        }.flowOn(Dispatchers.IO)
    }
}