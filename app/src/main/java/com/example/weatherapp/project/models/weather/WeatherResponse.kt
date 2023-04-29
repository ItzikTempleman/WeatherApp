package com.example.weatherapp.project.models.weather

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.project.utils.Constants

import com.example.weatherapp.project.models.BaseModel
import com.example.weatherapp.project.utils.Constants.WEATHER_RESPONSE_TABLE
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = WEATHER_RESPONSE_TABLE)
data class WeatherResponse(
    @SerializedName("coord")
    val coordinates: Coordinates = Coordinates(0.0, 0.0),
    val weather: List<Weather> = emptyList(),
    val main: Main = Main(0.0, 0.0 , 0.0, 0.0, 0 , 0),
    val visibility: Double = 0.0,
    val wind: Wind = Wind(0.0, 0),
    val clouds: Clouds = Clouds(0.0),
    @SerializedName("sys")
    val moreInfo: Sys = Sys("", 0.0, 0.0),
    val timezone: Int = -1,
    @SerializedName("name")
    val cityName: String = "Mock City Name",
    @SerializedName("cod")
    val code: Int = -1
) : Parcelable, BaseModel() {

    companion object {
        fun getMockObj() = WeatherResponse()
    }
}

@Entity
@Parcelize
data class Coordinates(
    val lon: Double,
    val lat: Double
) : Parcelable


@Entity
@Parcelize
data class Clouds(
    val all: Double
): Parcelable

@Entity
@Parcelize
data class Wind(
    val speed: Double,
    val deg: Int
) : Parcelable

@Entity
@Parcelize
data class Weather(
    @PrimaryKey val id: Int,
    val main: String,
    val description: String,
    val icon: String
) :
    Parcelable {
    fun getImage() = Constants.WEATHER_ICON_URL + icon + Constants.WEATHER_ICON_URL_ENDING
}

@Entity
@Parcelize
data class Main(
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int
) : Parcelable


@Entity
@Parcelize
data class Sys (
    val country:String,
    @SerializedName("sunrise")
    val sunRise:Double,
    @SerializedName("sunset")
    val sunSet:Double,
): Parcelable