package com.example.weatherapp.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.project.models.current_weather.WeatherResponse

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ConditionAndHumidity(weatherData: WeatherResponse, modifier: Modifier) {

        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            val ( humidityIcon, humidityValue, conditionText, icon,windSpeed, windSpeedValue) = createRefs()

            val painter = rememberImagePainter(data = weatherData.weather[0].getImage())





            Image(
                modifier = Modifier
                    .constrainAs(windSpeed) {
                        top.linkTo(parent.top)
                        end.linkTo(icon.start)
                        start.linkTo(parent.start)
                    }
                    .height(80.dp)
                    .width(80.dp),
                painter = painterResource(R.drawable.wind),
                contentDescription = "wind"
            )
            Text(
                modifier = Modifier
                    .constrainAs(windSpeedValue) {
                        top.linkTo(windSpeed.bottom)
                        end.linkTo(icon.start)
                        start.linkTo(parent.start)
                    },
                fontSize = 16.sp,
                text = convertFromMilesToKm(weatherData.wind.speed).toInt().toString() + " km/h"
            )


            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .constrainAs(icon) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(windSpeed.top)
                    },
                painter = painter,
                contentDescription = "icon"
            )
            Text(
                modifier = Modifier
                    .constrainAs(conditionText) {
                        top.linkTo(windSpeed.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                fontSize = 16.sp,
                text = capitalizeDesc(weatherData.weather[0].description)
            )
            Image(
                modifier = Modifier.padding(top = 20.dp)
                    .height(60.dp)
                    .width(60.dp)
                    .constrainAs(humidityIcon) {
                        start.linkTo(icon.end)
                        end.linkTo(parent.end)
                        top.linkTo(windSpeed.top)
                    },
                painter = painterResource(R.drawable.humidity),
                contentDescription = "humidity"
            )

            Text(
                modifier = Modifier
                    .constrainAs(humidityValue) {
                        start.linkTo(icon.end)
                        end.linkTo(parent.end)
                        top.linkTo(windSpeed.bottom)
                    },
                fontSize = 16.sp,
                text = weatherData.main.humidity.toString() + "%"
            )
        }
    }




