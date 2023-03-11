package com.example.weatherapp.project.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.project.models.Weather
import com.example.weatherapp.project.repositories.AppRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherRequestViewModel
@Inject constructor(private val repository: AppRepositoryImp) : ViewModel() {
    suspend fun getWeatherData(unit: String, cityName: String): MutableLiveData<Weather> {
        val weatherLiveData: MutableLiveData<Weather> = MutableLiveData()

        viewModelScope.launch {
            val response = repository.getWeather(unit, cityName)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    weatherLiveData.value = body
                } else Log.d("TAG", "first failure message: " + response.message())
                return@launch
            } else Log.d("TAG", "second failure message: " + response.message())
            return@launch
        }
        return weatherLiveData
    }
}