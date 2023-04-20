package com.example.weatherapp.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.project.models.current_weather.WeatherResponse

@Composable
fun WindData(weatherData: WeatherResponse, modifier: Modifier) {
    Card(
        modifier = modifier
            .width(160.dp)
            .padding(12.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        elevation = 8.dp
    ) {
        ConstraintLayout(
            modifier = modifier.background(
                colorResource(id = R.color.very_light_blue)
            )
        ) {
            val (dividerLine, windSpeed, windDirection, windGraph) = createRefs()

            Divider(
                color = Color.Black, modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .constrainAs(dividerLine) {
                        end.linkTo(parent.end)
                    }
            )
            Text(
                modifier = Modifier
                    .constrainAs(windSpeed) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                fontSize = 20.sp,
                text = "Wind speed " + weatherData.wind.speed.toInt().toString() + " km/h"
            )
        }
    }
}