package com.example.weatherapp.project.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.weatherapp.project.models.forecast.ForecastItem
import com.example.weatherapp.project.models.forecast.ForecastResponse


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ForecastLayout(
    modifier: Modifier,
    forecastData: ForecastResponse
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        elevation = 8.dp
    ) {
        LazyRow(modifier = modifier) {
            items(items = forecastData.hourlyList, itemContent = {
                ForecastLayout(it, modifier)
            })
        }
    }
}

@Composable
fun ForecastLayout(forecast: ForecastItem, modifier: Modifier) {
    Column(
        modifier = modifier
            .width(150.dp)
    ) {
        Text(text =forecast.main.temp.toInt().toString())
    }
}











