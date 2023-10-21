package com.itzik.weatherapp.project.utils

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