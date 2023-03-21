package com.example.weatherapp.project.viewmodels

import android.util.Log
import androidx.compose.ui.input.key.Key.Companion.D
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.project.models.WeatherResponse
import com.example.weatherapp.project.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {
    fun getWeatherResponse(city: String): Flow<WeatherResponse> {
        val weatherListFlow = flow {
            val response = repository.getWeather(city)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    emit(responseBody)
                } else Log.d("TAG", "first failure message: " + response.message())
                return@flow
            } else Log.d("TAG", "second failure message: " + response.message())
            return@flow
        }
        return weatherListFlow
    }
}


