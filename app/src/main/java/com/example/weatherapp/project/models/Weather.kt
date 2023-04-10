package com.example.weatherapp.project.models

import android.os.Parcelable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.Entity
import androidx.room.PrimaryKey
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.weatherapp.project.main.Constants.WEATHER_ICON_URL
import com.example.weatherapp.project.main.Constants.WEATHER_ICON_URL_ENDING
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Weather(@PrimaryKey val id: Int, val main: String, val description: String, val icon: String) : Parcelable {
        fun getImage() = WEATHER_ICON_URL + icon + WEATHER_ICON_URL_ENDING





//        fun replaceImage() {
//                when (icon) {
//                        "01d" -> {
//                                changeBackGround("sun")
//                        }
//                        "02d" -> {
//                                changeBackGround("partialSun")
//                        }
//
//                        "03d" -> {
//                                changeBackGround("dayClouds")
//                        }
//                        "11d" -> {
//                                changeBackGround("dayLightning")
//                        }
//                        "13d" -> {
//                                changeBackGround("daySnow")
//                        }
//                        "50d" -> {
//                                changeBackGround("haze")
//                        }
//                        "01n" -> {
//                                changeBackGround("clearNight")
//                        }
//                        "02n" -> {
//                                changeBackGround("partialMoon")
//                        }
//                        "03n" -> {
//                                changeBackGround("nightClouds")
//                        }
//                        "11n" -> {
//                                changeBackGround("nightLightning")
//                        }
//                        "13n" -> {
//                                changeBackGround("nightSnow")
//                        }
//                        "50n" -> {
//                                changeBackGround("nightHaze")
//                        }
//                }
//
//        }
}

fun changeBackGround(imageName: String) {

}






