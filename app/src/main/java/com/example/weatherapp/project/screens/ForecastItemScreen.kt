package com.example.weatherapp.project.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapp.project.models.forecast.ForecastResponse


@Composable
fun ForecastItem(forecast:ForecastResponse) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(120.dp)
            .border(BorderStroke(0.75.dp, Color.Black))
            .clip(shape = RoundedCornerShape(8.dp))
    ) {
        for (forecastItem in forecast.hourlyList)
        val time=
        Text(
    text =
        )
    }
}