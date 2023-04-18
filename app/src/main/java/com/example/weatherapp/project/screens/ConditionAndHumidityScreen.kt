package com.example.weatherapp.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
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
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        elevation = 8.dp
    ) {
        ConstraintLayout(
            modifier = modifier
                .background(
                    colorResource(id = R.color.very_light_blue)
                )
                .padding(6.dp)
        ) {
            val (humidityIcon, humidityValue, conditionText, icon) = createRefs()

            val painter = rememberImagePainter(data = weatherData.weather[0].getImage())


            Image(
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
                    .constrainAs(icon) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                    },
                painter = painter,
                contentDescription = "icon"
            )
            Text(
                modifier = Modifier
                    .constrainAs(conditionText) {
                        top.linkTo(icon.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                fontSize = 16.sp,
                text = capitalizeDesc(weatherData.weather[0].description)
            )

            Image(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .height(20.dp)
                    .width(20.dp)
                    .constrainAs(humidityIcon) {
                        start.linkTo(icon.end)
                        top.linkTo(icon.top)
                        bottom.linkTo(icon.bottom)
                    },
                painter = painterResource(R.drawable.humidity),
                contentDescription = "humidity"
            )

            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .constrainAs(humidityValue) {
                        start.linkTo(humidityIcon.end)
                        top.linkTo(icon.top)
                        bottom.linkTo(icon.bottom)
                    },
                fontSize = 20.sp,
                text = weatherData.main.humidity.toString() + "%"
            )

        }
    }
}



