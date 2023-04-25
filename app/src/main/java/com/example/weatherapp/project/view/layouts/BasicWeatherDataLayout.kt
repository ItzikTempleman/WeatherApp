package com.example.weatherapp.project.view.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.project.models.weather.WeatherResponse
import com.example.weatherapp.project.view.convertFromFahrenheitToCelsius
import com.example.weatherapp.project.view.getFullCountryName

@Composable
fun BasicWeatherData(weatherModel: WeatherResponse, modifier: Modifier) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        val (cityNameText, countryNameText, temperature, mainDegreesIcon, feelsLike, feelsLikeDegreesIcon, lowLayout, highLayout) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(cityNameText) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            text = weatherModel.cityName,
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
            text = getFullCountryName(weatherModel.moreInfo.country),
            fontSize = 16.sp
        )

        Text(
            modifier = Modifier
                .padding(top = 30.dp)
                .constrainAs(temperature) {
                    top.linkTo(countryNameText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            fontSize = 44.sp,
            text = convertFromFahrenheitToCelsius(weatherModel.main.temp).toInt()
                .toString()
        )

        Text(
            modifier = Modifier
                .padding(top = 30.dp)
                .constrainAs(mainDegreesIcon) {
                    top.linkTo(temperature.top)
                    start.linkTo(temperature.end)
                },
            fontSize = 20.sp,
            text = "o"
        )
        Text(modifier = Modifier
            .padding(top = 30.dp)
            .constrainAs(feelsLike) {
                top.linkTo(temperature.top)
                start.linkTo(countryNameText.start)
                bottom.linkTo(temperature.bottom)
            }
            .padding(start = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            text = "Feels like ${convertFromFahrenheitToCelsius(weatherModel.main.feelsLike).toInt()}"

        )

        Text(
            modifier = Modifier
                .constrainAs(feelsLikeDegreesIcon) {
                    top.linkTo(feelsLike.top)
                    start.linkTo(feelsLike.end)
                }
                .padding(start = 1.dp, top = 22.dp),
            fontSize = 12.sp,
            text = "o"
        )


        Row(
            modifier = Modifier
                .padding(top = 30.dp)
                .constrainAs(highLayout) {
                    top.linkTo(temperature.top)
                    bottom.linkTo(temperature.bottom)
                    start.linkTo(lowLayout.end)
                    end.linkTo(temperature.start)
                }
                .background(colorResource(id = R.color.light_yellow))
        ) {

            Image(
                modifier = Modifier
                    .height(28.dp)
                    .width(28.dp),
                painter = painterResource(R.drawable.hot),
                contentDescription = "hot_icon",
                contentScale = ContentScale.FillBounds
            )

            Text(
                modifier = Modifier,
                fontSize = 16.sp,
                text = convertFromFahrenheitToCelsius(weatherModel.main.tempMax).toInt()
                    .toString(),
                fontWeight = FontWeight.Bold
            )
        }




        Row(
            modifier = Modifier
                .padding(top = 30.dp)
                .constrainAs(lowLayout) {
                    top.linkTo(temperature.top)
                    bottom.linkTo(temperature.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(highLayout.start)
                }
                .background(colorResource(id = R.color.light_blue))

        ) {

            Image(
                modifier = Modifier

                    .height(28.dp)
                    .width(28.dp),
                painter = painterResource(R.drawable.cold),
                contentDescription = "cold_icon",
                contentScale = ContentScale.FillBounds
            )

            Text(
                modifier = Modifier,
                fontSize = 16.sp,
                text = convertFromFahrenheitToCelsius(weatherModel.main.tempMin).toInt()
                    .toString(),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
    
