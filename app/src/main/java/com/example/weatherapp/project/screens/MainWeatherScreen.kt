package com.example.weatherapp.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.project.models.current_weather.WeatherResponse

@Composable
fun MainWeather(weatherData: WeatherResponse, modifier: Modifier) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (cityNameText, countryNameText, temperature, feelsLike, degrees, max, sun, min, moon) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(cityNameText) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            text = weatherData.cityName,
            fontSize = 32.sp
        )

        Text(
            modifier = Modifier
                .constrainAs(countryNameText) {
                    end.linkTo(parent.end)
                    top.linkTo(cityNameText.bottom)
                    start.linkTo(parent.start)
                },
            text = getFullCountryName(weatherData.moreInfo.country),

            fontSize = 16.sp
        )

        Text(modifier = Modifier
            .constrainAs(feelsLike) {
                top.linkTo(temperature.top)
                start.linkTo(degrees.end)
                bottom.linkTo(temperature.bottom)
            }
            .padding(start = 4.dp),
            fontSize = 18.sp,
            text = "(feels like ${convertFromFahrenheitToCelsius(weatherData.main.feelsLike).toInt()})"
        )



        Text(
            modifier = Modifier
                .constrainAs(temperature) {
                    top.linkTo(countryNameText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            fontSize = 54.sp,
            text = convertFromFahrenheitToCelsius(weatherData.main.temp).toInt()
                .toString()
        )
        Text(
            modifier = Modifier
                .constrainAs(degrees) {
                    top.linkTo(temperature.top)
                    start.linkTo(temperature.end)
                },
            fontSize = 20.sp,
            text = "o"
        )


        Image(modifier = Modifier
            .padding(8.dp)
            .constrainAs(sun) {
                top.linkTo(temperature.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
            .height(20.dp)
            .width(20.dp),
            painter = painterResource(R.drawable.sun),
            contentDescription = "sun",
            contentScale = ContentScale.FillBounds
        )

        Text(
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(max) {
                    top.linkTo(temperature.bottom)
                    start.linkTo(sun.end)

                },
            fontSize = 16.sp,
            text = convertFromFahrenheitToCelsius(weatherData.main.tempMax).toInt()
                .toString(),
            fontWeight = FontWeight.Bold
        )

        Image(modifier = Modifier
            .padding(8.dp)
            .constrainAs(moon) {
                top.linkTo(sun.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
            .height(16.dp)
            .width(16.dp),
            painter = painterResource(R.drawable.moon),
            contentDescription = "moon",
            contentScale = ContentScale.FillBounds
        )

        Text(
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(min) {
                    top.linkTo(sun.bottom)
                    start.linkTo(moon.end)
                },
            fontSize = 16.sp,
            text = convertFromFahrenheitToCelsius(weatherData.main.tempMin).toInt().toString(),
            fontWeight = FontWeight.Bold
        )
    }
}