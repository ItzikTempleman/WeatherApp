package com.example.weatherapp.project.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.project.models.WeatherResponse
import com.example.weatherapp.project.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {

    fun getWeatherResponse(unit: String, city: String): MutableLiveData<WeatherResponse> {
        val weatherResponseLiveData: MutableLiveData<WeatherResponse> = MutableLiveData()
        viewModelScope.launch {
            val response = repository.getWeather(unit, city)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    weatherResponseLiveData.value = responseBody
                } else Log.d("TAG", "first failure message: " + response.message())
                return@launch
            } else Log.d("TAG", "second failure message: " + response.message())
            return@launch
        }
        return weatherResponseLiveData
    }
}