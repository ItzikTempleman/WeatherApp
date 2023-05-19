package com.example.weatherapp.project.view.layouts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.project.models.weather.WeatherResponse

@Composable
fun LazyRowLayout(
    weatherModel: WeatherResponse,
    modifier: Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
//        items(items = forecastData.updatedHourlyList(), itemContent = {
//            ForecastItem(it, modifier)
//        }
//        )
    }
}
