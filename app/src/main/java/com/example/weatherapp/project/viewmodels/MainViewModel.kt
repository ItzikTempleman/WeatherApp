package com.example.weatherapp.project.viewmodels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.project.models.forecast.ForecastResponse
import com.example.weatherapp.project.models.weather.WeatherResponse
import com.example.weatherapp.project.repositories.RepositoryImp
import com.example.weatherapp.project.view.toggleProgressBar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val repositoryImp: RepositoryImp
) : ViewModel() {

    private var cityNameFlow = flowOf("")
    private val cityNameLiveData = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            cityNameFlow.collect { cityName ->
                cityNameLiveData.value = cityName
            }
        }
    }

    fun setCityNameLiveData(cityName: String) {
        cityNameLiveData.value = cityName
    }

    fun getCityNameLiveData() = cityNameLiveData

    fun getWeatherResponse(city: String): Flow<WeatherResponse> {
        val weatherListFlow = flow {
            val response = repositoryImp.getWeather(city)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    emit(responseBody)
                    toggleProgressBar()
                } else Log.d("TAG", "first failure message: " + response.message())
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
            val forecastResponse = repositoryImp.getForecast(lat, lon)
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

    fun getImageResponse( cityImage: String): Flow<String> {
        val imageResponseFlow: Flow<String> = flow {
            val imageResponse = repositoryImp.getLocationImage(cityImage)
            if (imageResponse.isSuccessful) {
                val imageResponseBody = imageResponse.body()
                if (imageResponseBody != null) {
                   val image=imageResponseBody.cityResponse.resultResponse.results[4].image.photo.photoSizes.last().url
                    emit(image)
                } else Log.d(
                    "TAG", "second forecast failure message: " + imageResponse.message()
                )
                return@flow
            } else Log.d("TAG", "first forecast failure message: " + imageResponse.message())
            return@flow
        }
        return imageResponseFlow
    }

}




