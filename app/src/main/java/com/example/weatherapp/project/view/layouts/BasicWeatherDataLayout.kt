package com.example.weatherapp.project.view.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.project.models.weather.WeatherResponse
import com.example.weatherapp.project.utils.convertFromFahrenheitToCelsius
import com.example.weatherapp.project.utils.getFullCountryName

@Composable
fun TopWeatherData(weatherModel: WeatherResponse, modifier: Modifier) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        val (cityNameText, countryNameText, temperature, mainDegreesIcon, feelsLike, lowLayout, highLayout) = createRefs()
        createHorizontalChain(lowLayout,feelsLike, highLayout, chainStyle = ChainStyle.Spread)

        val offset = Offset(4.0f, 4.0f)

        Text(
            modifier = Modifier
                .constrainAs(cityNameText) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            text = weatherModel.cityName,
            fontSize = 28.sp,
            color = Color.White,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black, offset = offset, blurRadius = 4f
                )
            )
        )

        Text(
            color = Color.White,
            modifier = Modifier
                .constrainAs(countryNameText) {
                    top.linkTo(cityNameText.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
            text = getFullCountryName(weatherModel.moreInfo.country),

            fontSize = 16.sp,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black, offset = offset, blurRadius = 4f
                )
            )
        )

        Text(
            color = Color.White,
            modifier = Modifier.padding(top = 30.dp)
                .constrainAs(temperature) {
                    top.linkTo(countryNameText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            fontSize = 48.sp,
            text = convertFromFahrenheitToCelsius(weatherModel.main.temp).toInt().toString(),
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black, offset = offset, blurRadius = 4f
                )
            )
        )

        Text(
            color = Color.White,
            modifier = Modifier.padding(top = 30.dp)
                .constrainAs(mainDegreesIcon) {
                    top.linkTo(temperature.top)
                    start.linkTo(temperature.end)
                },
            fontSize = 24.sp,
            text = "o",
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black, offset = offset, blurRadius = 4f
                )
            )
        )

        Row(
            modifier = Modifier.padding(top = 30.dp)
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
                color = Color.White,
                modifier = Modifier.padding(bottom = 12.dp),
                fontSize = 12.sp,
                text = "Low: ",
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black, offset = offset, blurRadius = 4f
                    )
                )

                )
            Text(
                color = Color.White,
                modifier = Modifier,
                fontSize = 20.sp,
                text = convertFromFahrenheitToCelsius(weatherModel.main.tempMin).toInt()
                    .toString(),
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black, offset = offset, blurRadius = 4f
                    )
                )
            )
            Text(
                color = Color.White,
                modifier = Modifier.padding(bottom = 12.dp),
                fontSize = 12.sp,
                text = "o",
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black, offset = offset, blurRadius = 4f
                    )
                )
            )
        }

        Row(
            modifier = Modifier.padding(top = 30.dp)
                .constrainAs(feelsLike) {
                    top.linkTo(temperature.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                color = Color.White,
                modifier = Modifier,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "Feels like ${convertFromFahrenheitToCelsius(weatherModel.main.feelsLike).toInt()}",
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black, offset = offset, blurRadius = 4f
                    )
                )

            )

            Text(
                color = Color.White,
                modifier = Modifier,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = "o",
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black, offset = offset, blurRadius = 4f
                    )
                )
            )
        }

        Row(
            modifier = Modifier.padding(top = 30.dp)
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
                color = Color.White,
                modifier = Modifier.padding(bottom=12.dp),
                fontSize = 12.sp,
                text = "High: ",
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black, offset = offset, blurRadius = 4f
                    )
                )
            )
            Text(
                color = Color.White,
                modifier = Modifier,
                fontSize =20.sp,
                text = convertFromFahrenheitToCelsius(weatherModel.main.tempMax).toInt()
                    .toString(),
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black, offset = offset, blurRadius = 4f
                    )
                )
            )
            Text(
                color = Color.White,
                modifier = Modifier.padding(bottom = 12.dp),
                fontSize = 12.sp,
                text = "o",
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black, offset = offset, blurRadius = 4f
                    )
                )
            )
        }
    }
}
    
