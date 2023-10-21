package com.itzik.weatherapp.project.viewmodels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.project.view.toggleProgressBar
import com.itzik.weatherapp.project.models.forecast.ForecastResponse
import com.itzik.weatherapp.project.models.location_images.ImageResponse
import com.itzik.weatherapp.project.models.weather.WeatherResponse
import com.itzik.weatherapp.project.repositories.RepositoryImp
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



    fun getImages(city: String, clientId: String): Flow<ImageResponse> {

        val imageList: Flow<ImageResponse> = flow {
            val imageResponse =
                repositoryImp.getLocationImage(city, clientId)
            if (imageResponse.isSuccessful) {
                val unsplashImageListBody = imageResponse.body()
                if (unsplashImageListBody != null) {
                    emit(unsplashImageListBody)
                } else Log.d(
                    "TAG", "second image list failure message: " + imageResponse.message()
                )
                return@flow
            } else Log.d(
                "TAG",
                "first  image list message: " + imageResponse.message()
            )
            return@flow
        }
        return imageList
    }
}



