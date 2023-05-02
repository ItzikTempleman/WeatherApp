package com.example.weatherapp.project.viewmodels


import android.location.Address
import android.location.Geocoder
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.weatherapp.project.main.MainActivity
import com.example.weatherapp.project.models.weather.WeatherResponse
import com.example.weatherapp.project.models.forecast.ForecastResponse
import com.example.weatherapp.project.repositories.Repository
import com.example.weatherapp.project.view.toggleProgressBar
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
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
                    toggleProgressBar()                } else Log.d("TAG", "first failure message: " + response.message())
                toggleProgressBar()
                return@flow
            } else Log.d("TAG", "second failure message: " + response.message())
            toggleProgressBar()
            return@flow
        }
        return weatherListFlow
    }

    fun getForecastResponse(lat: Double, lon: Double): Flow<ForecastResponse> {
        val forecastFlow: Flow<ForecastResponse> = flow {
            val forecastResponse = repository.getForecast(lat, lon)
            if (forecastResponse.isSuccessful) {
                val forecastResponseBody = forecastResponse.body()
                if (forecastResponseBody != null) {
                   Log.d("TG", "response: ${forecastResponseBody.city}")
                    emit(forecastResponseBody)
                } else Log.d(
                    "TAG", "second forecast failure message: " + forecastResponse.message()
                )
                return@flow
            } else Log.d("TAG", "first forecast failure message: " + forecastResponse.message())
            return@flow
        }
        return forecastFlow
    }
}




