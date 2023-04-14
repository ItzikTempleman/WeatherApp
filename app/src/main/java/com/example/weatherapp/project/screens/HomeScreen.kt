package com.example.weatherapp.project.screens


import android.view.KeyEvent.KEYCODE_ENTER
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.project.main.getEmptyData
import com.example.weatherapp.project.main.getForecastEmptyData
import com.example.weatherapp.project.models.forecast.ForecastItem
import com.example.weatherapp.project.viewmodels.MainViewModel

var isSearched = mutableStateOf(false)
var weatherModel = getEmptyData()
var forecast = getForecastEmptyData()
var forecastItems: List<ForecastItem> = getForecastEmptyData().hourlyList
var isProgressBarVisible = mutableStateOf(false)

@OptIn(ExperimentalComposeUiApi::class, ExperimentalCoilApi::class)
@Composable
fun HomeScreen(mainViewModel: MainViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val (focusRequester) = FocusRequester.createRefs()

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(R.drawable.wall),
        contentDescription = "background_image",
        contentScale = ContentScale.FillBounds
    )

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (progressbar, searchET, cityNameText, countryNameText, humidityIcon, humidityValue, conditionText, temperature, feelsLike, degrees, icon, max, sun, min, moon) = createRefs()

        GenerateProgressBar(
            modifier = Modifier
                .width(44.dp)
                .height(44.dp)
                .constrainAs(progressbar) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            isVisible = isProgressBarVisible.value
        )

        MainTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .constrainAs(searchET) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }

            .onKeyEvent {
                if (it.nativeKeyEvent.keyCode == KEYCODE_ENTER) {
                    focusRequester.requestFocus()
                }
                false
            },
            coroutineScope = coroutineScope,
            mainViewModel = mainViewModel
        )


        if (isSearched.value) {
            val painter = rememberImagePainter(data = weatherModel.weather[0].getImage())

            Text(
                modifier = Modifier
                    .constrainAs(cityNameText) {
                        end.linkTo(parent.end)
                        top.linkTo(searchET.bottom)
                        start.linkTo(parent.start)
                    },
                text = weatherModel.cityName,
                fontSize = 32.sp
            )

            Text(
                modifier = Modifier
                    .constrainAs(countryNameText) {
                        end.linkTo(parent.end)
                        top.linkTo(cityNameText.bottom)
                        start.linkTo(parent.start)
                    },
                text = getFullCountryName(weatherModel.moreInfo.country),

                fontSize = 16.sp
            )

            Image(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .height(20.dp)
                    .width(20.dp)
                    .constrainAs(humidityIcon) {
                        start.linkTo(countryNameText.end)
                        top.linkTo(countryNameText.top)
                        bottom.linkTo(countryNameText.bottom)
                    },
                painter = painterResource(R.drawable.humidity),
                contentDescription = "humidity"
            )

            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .constrainAs(humidityValue) {
                        start.linkTo(humidityIcon.end)
                        top.linkTo(countryNameText.top)
                        bottom.linkTo(countryNameText.bottom)
                    },
                fontSize = 20.sp,
                text = weatherModel.main.humidity.toString() + "%"
            )

            Text(
                modifier = Modifier
                    .constrainAs(temperature) {
                        top.linkTo(countryNameText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                fontSize = 54.sp,
                text = convertFromFahrenheitToCelsius(weatherModel.main.temp).toInt()
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

            Text(modifier = Modifier
                .constrainAs(feelsLike) {
                    top.linkTo(temperature.top)
                    start.linkTo(degrees.end)
                    bottom.linkTo(temperature.bottom)
                }
                .padding(start = 4.dp),
                fontSize = 18.sp,
                text = "(feels like ${convertFromFahrenheitToCelsius(weatherModel.main.feelsLike).toInt()})"
            )

            Image(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .constrainAs(icon) {
                        end.linkTo(cityNameText.start)
                        start.linkTo(parent.start)
                        bottom.linkTo(cityNameText.bottom)
                        top.linkTo(cityNameText.top)
                    },
                painter = painter,
                contentDescription = "icon"
            )

            Text(
                modifier = Modifier
                    .constrainAs(conditionText) {
                        top.linkTo(icon.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(countryNameText.start)
                    },
                fontSize = 16.sp,
                text = capitalizeDesc(weatherModel.weather[0].description)
            )

            Image(modifier = Modifier
                .padding(8.dp)
                .constrainAs(sun) {
                    top.linkTo(conditionText.bottom)
                    end.linkTo(temperature.start)
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
                        top.linkTo(conditionText.bottom)
                        start.linkTo(sun.end)

                    },
                fontSize = 16.sp,
                text = convertFromFahrenheitToCelsius(weatherModel.main.tempMax).toInt()
                    .toString(),
                fontWeight = FontWeight.Bold
            )

            Image(modifier = Modifier
                .padding(8.dp)
                .constrainAs(moon) {
                    top.linkTo(sun.bottom)
                    end.linkTo(sun.end)
                    start.linkTo(sun.start)
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
                text =
                convertFromFahrenheitToCelsius(weatherModel.main.tempMin).toInt().toString(),
                fontWeight = FontWeight.Bold
            )
        } else getEmptyData().cityName
    }
}


