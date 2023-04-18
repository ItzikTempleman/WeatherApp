package com.example.weatherapp.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.project.models.current_weather.WeatherResponse

@Composable
fun WindData(weatherData: WeatherResponse, modifier: Modifier) {
    ConstraintLayout(
        modifier = modifier
            .padding(6.dp)
            .background(colorResource(id = R.color.light_yellow))
    ) {
        val (windSpeed, windDirection, windGraph) = createRefs()

    }
}