package com.example.weatherapp.project.view.layouts

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.project.models.forecast.ForecastItem
import com.example.weatherapp.project.models.forecast.ForecastResponse
import com.example.weatherapp.project.view.convertFromKelvinToCelsius


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
                ForecastItem(it, modifier)
            })
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ForecastItem(forecast: ForecastItem, modifier: Modifier) {
    ConstraintLayout(
        modifier = modifier
            .border(BorderStroke(0.1.dp, colorResource(id = R.color.dark_blue)))
            .width(150.dp)
    ) {
        val (date, dayOfWeek, hour, degrees, degreesPercent, icon, description, precipitation) = createRefs()

        val firstForecastOrNull = forecast.weatherInForecast.firstOrNull()
        val painter = rememberImagePainter(data = firstForecastOrNull?.getForecastImage())


        Text(
            modifier = Modifier
                .padding(top = 20.dp)
                .constrainAs(degrees) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            text = convertFromKelvinToCelsius(forecast.main.temp).toInt().toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,

            )
        Text(
            modifier = Modifier
                .padding(top = 16.dp, start = 2.dp)
                .constrainAs(degreesPercent) {
                    top.linkTo(parent.top)
                    start.linkTo(degrees.end)
                },
            text = "o",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )

        Image(
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(degrees.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .height(50.dp)
                .width(50.dp),
            painter = painter,
            contentDescription = "forecast_icon"
        )
    }
}











