package com.example.weatherapp.project.view.layouts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.project.models.weather.WeatherResponse
import com.example.weatherapp.project.view.screens.forecastModel

@Composable
fun DataLayout(
    weatherModel: WeatherResponse, modifier: Modifier
) {


    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        val ( mainLayout, conditionLayout, forecastLayout) = createRefs()

        LazyRowLayout(
            weatherModel = weatherModel, modifier = Modifier.fillMaxSize().padding(4.dp)
        )

        BasicWeatherData(
            weatherModel = weatherModel, modifier = Modifier
                .constrainAs(mainLayout) {
                    top.linkTo(parent.top)
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
                    bottom.linkTo(parent.bottom)
                }
                .height(225.dp)
        )
    }
}



