package com.example.weatherapp.project.view.layouts

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.project.models.forecast.ForecastItem
import com.example.weatherapp.project.models.forecast.ForecastResponse
import com.example.weatherapp.project.utils.convertFromKelvinToCelsius
import com.example.weatherapp.project.utils.getDate
import com.example.weatherapp.project.utils.getDayOfWeek
import com.example.weatherapp.project.utils.getHourOfDay


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ForecastLayout(
    modifier: Modifier,
    forecastData: ForecastResponse
) {

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(items = forecastData.updatedHourlyList(), itemContent = {
            ForecastItem(it, modifier)
        })
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ForecastItem(forecast: ForecastItem, modifier: Modifier) {
    ConstraintLayout(
        modifier = modifier
            .border(BorderStroke(0.05.dp, colorResource(id = R.color.black)))
            .width(125.dp)
    ) {
        val (dayOfWeek, date, hour, degrees, degreesPercent, icon, description, precipitationIcon, precipitation) = createRefs()
        val firstForecastOrNull = forecast.weatherInForecast.firstOrNull()
        val painter = rememberImagePainter(data = firstForecastOrNull?.getForecastImage())
        createHorizontalChain(precipitationIcon, precipitation, chainStyle = ChainStyle.Packed)

        Text(
            modifier = Modifier

                .constrainAs(dayOfWeek) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            text = getDayOfWeek(forecast.exactTime),
            fontSize = 18.sp
        )

        Text(
            modifier = Modifier

                .constrainAs(date) {
                    end.linkTo(parent.end)
                    top.linkTo(dayOfWeek.bottom)
                    start.linkTo(parent.start)
                },
            text = getDate(forecast.exactTime),
            fontSize = 18.sp
        )

        Text(
            modifier = Modifier

                .constrainAs(hour) {
                    end.linkTo(parent.end)
                    top.linkTo(date.bottom)
                    start.linkTo(parent.start)
                },
            text = getHourOfDay(forecast.exactTime),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier

                .constrainAs(degrees) {
                    end.linkTo(parent.end)
                    top.linkTo(hour.bottom)
                    start.linkTo(parent.start)
                },
            text = convertFromKelvinToCelsius(forecast.main.temp).toInt().toString(),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold

            )


        Text(
            modifier = Modifier
                .padding(2.dp)
                .constrainAs(degreesPercent) {
                    top.linkTo(degrees.top)
                    start.linkTo(degrees.end)
                },
            text = "o",
            fontSize = 16.sp,
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
        Text(
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(icon.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            text = forecast.weatherInForecast.first().description,
            fontSize = 16.sp
        )

        Image(
            modifier = Modifier
                .constrainAs(precipitationIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(description.bottom)
                }
                .height(26.dp)
                .width(26.dp),
            painter = painterResource(R.drawable.precipitation),
            contentDescription = "rain"
        )
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .constrainAs(precipitation) {
                    end.linkTo(parent.end)
                    bottom.linkTo(precipitationIcon.bottom)
                    top.linkTo(precipitationIcon.top)
                    end.linkTo(parent.end)
                },
            text = if(forecast.rain?.threeHours.toString()!="null") "${forecast.rain?.threeHours} MM" else "no rain",
            fontSize = 14.sp
        )
        }
    }