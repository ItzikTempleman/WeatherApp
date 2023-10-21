 package com.itzik.weatherapp.project.models.forecast

import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import com.itzik.weatherapp.project.utils.Constants
import kotlinx.parcelize.Parcelize

 @Parcelize
 data class ForecastResponse(
     @SerializedName("list")
     val hourlyList: List<ForecastItem> = emptyList(),
     val city: City = City(coord = CoordinatesInForecast(0.0, 0.0)),

     ) : Parcelable {
     companion object {
         fun getForecastMockObj() = ForecastResponse()

     }

     fun updatedHourlyList(): MutableList<ForecastItem> {
         val updatedList:MutableList<ForecastItem> =mutableListOf()
         updatedList.addAll(hourlyList)
         updatedList.removeFirst()
         return updatedList
     }

 }

 @Parcelize
 data class ForecastItem(
     val main: MainInForecast,
     @SerializedName("weather")
     val weatherInForecast: List<WeatherInForecast>,
     val rain: Rain? = null,
     @SerializedName("dt_txt")
     val exactTime: String,
 ) : Parcelable


 @Parcelize
 data class Rain(
     @SerializedName("3h")
     val threeHours: Double
     ) : Parcelable


 @Parcelize
 data class MainInForecast(
     val temp: Double
 ) : Parcelable


 @Parcelize
 data class WeatherInForecast(
     val main: String,
     val description: String,
     val icon: String
 ) : Parcelable {
     fun getForecastImage() = Constants.WEATHER_ICON_URL + icon + Constants.WEATHER_ICON_URL_ENDING
 }


 @Parcelize
 data class CoordinatesInForecast(
     val lat: Double,
     val lon: Double
 ) : Parcelable


 @Parcelize
 data class City(
     val coord: CoordinatesInForecast
 ) : Parcelable