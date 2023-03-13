package com.example.weatherapp.project.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.project.models.WeatherResponse
import com.example.weatherapp.project.repositories.Repository
import com.example.weatherapp.project.requests.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {

    private val weatherResponseLiveData: MutableLiveData<NetworkResult<WeatherResponse>> =
        MutableLiveData()
    val response: LiveData<NetworkResult<WeatherResponse>> = weatherResponseLiveData

    fun getWeatherResponse(unit: String, city: String) = viewModelScope.launch {
        repository.getWeatherData(unit, city).collect {
            weatherResponseLiveData.value = it
        }
    }
}