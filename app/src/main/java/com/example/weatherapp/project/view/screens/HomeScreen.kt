package com.example.weatherapp.project.view.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.project.models.forecast.ForecastResponse
import com.example.weatherapp.project.models.weather.WeatherResponse
import com.example.weatherapp.project.view.ProgressBar
import com.example.weatherapp.project.view.composables.SearchTextField
import com.example.weatherapp.project.view.layouts.BasicWeatherData
import com.example.weatherapp.project.view.layouts.ForecastLayout
import com.example.weatherapp.project.view.layouts.WindAndHumidity
import com.example.weatherapp.project.viewmodels.MainViewModel

var isSearched = mutableStateOf(false)
var weatherModel = WeatherResponse.getMockObj()
var forecastModel = ForecastResponse.getForecastMockObj()
var isProgressBarVisible = mutableStateOf(false)

@Composable
fun HomeScreen(mainViewModel: MainViewModel) {

    val coroutineScope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(id = R.color.light_teal),
                        colorResource(id = R.color.very_light_teal),
                        colorResource(id = R.color.light_teal)
                    )
                )
            )
    ) {
        val (progressbar, searchET, location, mainLayout, conditionLayout, forecastLayout) = createRefs()


        SearchTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .constrainAs(searchET) {
                    top.linkTo(parent.top)
                },
            coroutineScope = coroutineScope,
            mainViewModel = mainViewModel
        )


        if (isSearched.value) {

            BasicWeatherData(
                weatherModel = weatherModel, modifier = Modifier
                    .constrainAs(mainLayout) {
                        top.linkTo(searchET.bottom)
                    }
                    .padding(top = 50.dp)

            )

            WindAndHumidity(
                weatherData = weatherModel, modifier = Modifier
                    .constrainAs(conditionLayout) {
                        top.linkTo(mainLayout.bottom)
                    }
                    .padding(top = 50.dp)
            )

            ForecastLayout(
                forecastData = forecastModel, modifier = Modifier
                    .constrainAs(forecastLayout) {
                        bottom.linkTo(location.top)
                    }
                    .height(225.dp)
            )
        }

        Surface(
            modifier = Modifier
                .constrainAs(location) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .size(80.dp)
                .padding(16.dp),
            shape = CircleShape,
            elevation = 20.dp
        ) {
            FloatingActionButton(
                backgroundColor = colorResource(id = R.color.white),
                modifier = Modifier,
                onClick = {
                    // TODO: not implemented yet
                    //GetLocation()
                }
            ) {
                Image(
                    painter = painterResource(R.drawable.gps_location),
                    contentDescription = "location",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        ProgressBar(
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
    }
}





