package com.example.weatherapp.project.main

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.weatherapp.project.models.WeatherResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
/**

 @ProvidedTypeConverter
 class Converters {
    @TypeConverter
    fun fromWeatherResponse(weatherResponse:WeatherResponse):String {
        return Gson().toJson(weatherResponse)
    }
    @TypeConverter
    fun toWeatherResponse(weatherResponseString: String): WeatherResponse {
        return Gson().fromJson(weatherResponseString, object : TypeToken<WeatherResponse>() {}.type)
    }

}
*/