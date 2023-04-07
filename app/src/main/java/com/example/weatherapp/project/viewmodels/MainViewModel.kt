package com.example.weatherapp.project.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherapp.project.models.WeatherResponse
import com.example.weatherapp.project.repositories.Repository
import com.example.weatherapp.project.screens.GenerateProgressBar
import com.example.weatherapp.project.screens.stopProgressBar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
                    stopProgressBar()
                } else Log.d("TAG", "first failure message: " + response.message())
                stopProgressBar()
                return@flow
            } else Log.d("TAG", "second failure message: " + response.message())
            stopProgressBar()
            return@flow
        }
        return weatherListFlow
    }
}




