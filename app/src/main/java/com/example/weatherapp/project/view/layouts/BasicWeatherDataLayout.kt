package com.example.weatherapp.project.view.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
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
        val (cityNameText, countryNameText, temperature, mainDegreesIcon, feelsLike, lowLayout, highLayout) = createRefs()
        createHorizontalChain(lowLayout,feelsLike, highLayout, chainStyle = ChainStyle.Spread)
        Text(
            modifier = Modifier
                .constrainAs(cityNameText) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            text = weatherModel.cityName,
            fontSize = 28.sp
        )

        Text(
            modifier = Modifier
                .constrainAs(countryNameText) {
                    top.linkTo(cityNameText.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
            text = getFullCountryName(weatherModel.moreInfo.country),
            fontSize = 16.sp
        )

        Text(
            modifier = Modifier
                .constrainAs(temperature) {
                    top.linkTo(countryNameText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            fontSize = 36.sp,
            text = convertFromFahrenheitToCelsius(weatherModel.main.temp).toInt()
                .toString()
        )

        Text(
            modifier = Modifier
                .constrainAs(mainDegreesIcon) {
                    top.linkTo(temperature.top)
                    start.linkTo(temperature.end)
                },
            fontSize = 16.sp,
            text = "o"
        )

        Row(
            modifier = Modifier
                .constrainAs(lowLayout) {
                    top.linkTo(temperature.bottom)
                    start.linkTo(parent.start)
                }
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
                modifier = Modifier.padding(bottom = 12.dp),
                fontSize = 12.sp,
                text = "Low: ",

                )
            Text(
                modifier = Modifier,
                fontSize = 20.sp,
                text = convertFromFahrenheitToCelsius(weatherModel.main.tempMin).toInt()
                    .toString(),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                fontSize = 12.sp,
                text = "o",
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .constrainAs(feelsLike) {
                    top.linkTo(temperature.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                modifier = Modifier,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = "Feels like ${convertFromFahrenheitToCelsius(weatherModel.main.feelsLike).toInt()}"

            )

            Text(
                modifier = Modifier,
                fontSize = 12.sp,
                text = "o"
            )
        }

        Row(
            modifier = Modifier
                .constrainAs(highLayout) {
                    top.linkTo(temperature.bottom)
                    end.linkTo(parent.end)
                }
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
                modifier = Modifier.padding(bottom=12.dp),
                fontSize = 12.sp,
                text = "High: ",
            )
            Text(
                modifier = Modifier,
                fontSize =20.sp,
                text = convertFromFahrenheitToCelsius(weatherModel.main.tempMax).toInt()
                    .toString(),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                fontSize = 12.sp,
                text = "o",
                fontWeight = FontWeight.Bold
            )
        }
    }
}
    
