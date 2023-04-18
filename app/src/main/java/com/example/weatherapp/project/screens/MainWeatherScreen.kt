package com.example.weatherapp.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.project.models.current_weather.WeatherResponse

@Composable
fun MainWeather(weatherData: WeatherResponse, modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        elevation = 8.dp
    ) {
        ConstraintLayout(
            modifier = modifier
                .background(
                    colorResource(id = R.color.very_light_blue)
                )
                .padding(6.dp)
        ) {
            val (cityNameText, countryNameText, temperature,mainDegreesIcon, feelsLike, feelsLikeDegreesIcon, max, sun, min, moon) = createRefs()

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
                    .padding(top = 6.dp, start = 8.dp)
                    .constrainAs(countryNameText) {
                        top.linkTo(cityNameText.top)
                        bottom.linkTo(cityNameText.bottom)
                        start.linkTo(cityNameText.end)
                    },
                text = getFullCountryName(weatherData.moreInfo.country),
                fontSize = 16.sp
            )

            Text(modifier = Modifier
                .constrainAs(feelsLike) {
                    top.linkTo(temperature.top)
                    start.linkTo(countryNameText.start)
                    bottom.linkTo(temperature.bottom)
                }.padding(top = 6.dp, start = 8.dp),
                fontSize = 16.sp,
                text = "feels like ${convertFromFahrenheitToCelsius(weatherData.main.feelsLike).toInt()}"
            )

            Text(
                modifier = Modifier
                    .constrainAs(feelsLikeDegreesIcon) {
                        top.linkTo(feelsLike.top)
                        start.linkTo(feelsLike.end)
                    }.padding(start = 1.dp, bottom =8.dp),
                fontSize = 12.sp,
                text = "o"
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
                    .constrainAs(mainDegreesIcon) {
                        top.linkTo(temperature.top)
                        start.linkTo(temperature.end)
                    },
                fontSize = 20.sp,
                text = "o"
            )


            Image(modifier = Modifier

                .constrainAs(sun) {
                    top.linkTo(temperature.top)
                    end.linkTo(temperature.start)
                    start.linkTo(parent.start)
                }
                .height(28.dp)
                .width(28.dp),
                painter = painterResource(R.drawable.sun),
                contentDescription = "sun",
                contentScale = ContentScale.FillBounds
            )

            Text(
                modifier = Modifier
                    .padding(6.dp)
                    .constrainAs(max) {
                        top.linkTo(temperature.top)
                        start.linkTo(sun.end)

                    },
                fontSize = 16.sp,
                text = convertFromFahrenheitToCelsius(weatherData.main.tempMax).toInt()
                    .toString(),
                fontWeight = FontWeight.Bold
            )

            Image(modifier = Modifier

                .constrainAs(moon) {
                    top.linkTo(sun.bottom)
                    end.linkTo(temperature.start)
                    start.linkTo(parent.start)
                }
                .padding(8.dp)
                .height(20.dp)
                .width(20.dp),
                painter = painterResource(R.drawable.moon),
                contentDescription = "moon",
                contentScale = ContentScale.FillBounds
            )

            Text(
                modifier = Modifier
                    .padding(6.dp)
                    .constrainAs(min) {
                        top.linkTo(sun.bottom)
                        end.linkTo(max.end)
                    },
                fontSize = 16.sp,
                text = convertFromFahrenheitToCelsius(weatherData.main.tempMin).toInt().toString(),
                fontWeight = FontWeight.Bold
            )
        }
    }
}