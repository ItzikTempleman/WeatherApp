package com.example.weatherapp.project.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.project.models.forecast.ForecastResponse


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ForecastLayout(
    modifier: Modifier,
    forecastData: ForecastResponse
) {
    Log.d("TAG","forecastData: $forecastData")
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        elevation = 8.dp
    ) {
        Row(
            modifier = modifier
                .background(
                    colorResource(id = R.color.very_light_blue)
                )
                .padding(6.dp)
        ) {

            LazyRow(
                modifier = modifier
            ) {
                items(items = forecastData.hourlyList, itemContent = {
                    Text(text = it.exactTime)
                })
            }
        }
    }
}








