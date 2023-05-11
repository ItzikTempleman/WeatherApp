package com.example.weatherapp.project.view.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.project.models.weather.WeatherResponse
import com.example.weatherapp.project.utils.capitalizeDesc
import com.example.weatherapp.project.utils.convertFromMilesToKm

@OptIn(ExperimentalCoilApi::class)
@Composable
fun WindAndHumidity(weatherData: WeatherResponse, modifier: Modifier) {

        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            val ( humidityIcon, humidityValue, conditionIcon,conditionText,windSpeedIcon, windSpeedValue) = createRefs()

            val firstWeatherOrNull = weatherData.weather.firstOrNull()
            val painter = rememberImagePainter(data = firstWeatherOrNull?.getImage())

            Image(
                modifier = Modifier
                    .constrainAs(windSpeedIcon) {
                        top.linkTo(parent.top)
                        end.linkTo(conditionIcon.start)
                        start.linkTo(parent.start)
                    }
                    .height(50.dp)
                    .width(50.dp),
                painter = painterResource(R.drawable.wind),
                contentDescription = "wind_icon"
            )
            Text(
                modifier = Modifier
                    .constrainAs(windSpeedValue) {
                        top.linkTo(windSpeedIcon.bottom)
                        end.linkTo(conditionIcon.start)
                        start.linkTo(parent.start)
                    },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = convertFromMilesToKm(weatherData.wind.speed).toInt().toString() + " km/h"
            )


            Image(
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
                    .constrainAs(conditionIcon) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(windSpeedIcon.top)
                        bottom.linkTo(windSpeedIcon.bottom)
                    },
                painter = painter,
                contentDescription = "condition_icon"
            )
            Text(
                modifier = Modifier
                    .constrainAs(conditionText) {
                        top.linkTo(windSpeedValue.top)
                        bottom.linkTo(windSpeedValue.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = capitalizeDesc(weatherData.weather[0].description)
            )
            Image(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .constrainAs(humidityIcon) {
                        start.linkTo(conditionIcon.end)
                        end.linkTo(parent.end)
                        top.linkTo(windSpeedIcon.top)
                        bottom.linkTo(windSpeedIcon.bottom)
                    },
                painter = painterResource(R.drawable.humidity_level),
                contentDescription = "humidity_icon"
            )

            Text(
                modifier = Modifier
                    .constrainAs(humidityValue) {
                        start.linkTo(conditionIcon.end)
                        end.linkTo(parent.end)
                        top.linkTo(windSpeedValue.top)
                        bottom.linkTo(windSpeedValue.bottom)
                    },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = weatherData.main.humidity.toString()
            )
        }
    }




