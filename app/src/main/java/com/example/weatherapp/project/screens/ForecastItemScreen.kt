package com.example.weatherapp.project.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.project.models.forecast.ForecastItem
import com.example.weatherapp.project.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ForecastLayout(
    mainViewModel: MainViewModel,
    coroutineScope: CoroutineScope,

) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            coroutineScope.launch {
                mainViewModel.getForecastResponse(
                    weatherModel.coordinates.lat,
                    weatherModel.coordinates.lon
                ).collect {
                    forecastItems = it.hourlyList

                }

            }
            LoadForecastList(forecastItems)
        }
    }




        @Composable
        fun LoadForecastList(forecastItemsWithData: List<ForecastItem>) {
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .background(colorResource(id = R.color.light_blue))
            ) {
                items(items = forecastItemsWithData, itemContent = {
                    Text(text = it.exactTime)
                })
            }
        }



