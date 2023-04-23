package com.example.weatherapp.project.screens


import android.annotation.SuppressLint
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.project.main.getEmptyData
import com.example.weatherapp.project.main.getForecastEmptyData
import com.example.weatherapp.project.models.forecast.ForecastItem
import com.example.weatherapp.project.viewmodels.MainViewModel
import kotlinx.coroutines.launch


var isSearched = mutableStateOf(false)
var weatherModel = getEmptyData()
var forecastModel = getForecastEmptyData()
var isProgressBarVisible = mutableStateOf(false)

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(mainViewModel: MainViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val (focusRequester) = FocusRequester.createRefs()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (progressbar, searchET, location, mainLayout, conditionLayout, forecastLayout) = createRefs()

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
            .padding(6.dp)
            .width(360.dp)
            .constrainAs(searchET) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(location.start)
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

        Image(
            modifier = Modifier
                .clickable {
                    getLocation()
                }
                .clip(shape = RoundedCornerShape(10.dp))
                .constrainAs(location) {
                    top.linkTo(searchET.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(searchET.bottom)
                }
                .size(45.dp)
                .padding(12.dp),
            painter = painterResource(R.drawable.gps_location),
            contentDescription = "location"
        )

        if (isSearched.value) {

            coroutineScope.launch {
                mainViewModel.getForecastResponse(
                    weatherModel.coordinates.lat,
                    weatherModel.coordinates.lon
                ).collect { forecastIt ->
                    forecastModel = forecastIt
                }
            }


            MainWeather(
                weatherData = weatherModel, modifier = Modifier
                    .constrainAs(mainLayout) {
                        top.linkTo(searchET.bottom)
                    }

            )

            ConditionAndHumidity(
                weatherData = weatherModel, modifier = Modifier
                    .constrainAs(conditionLayout) {
                        top.linkTo(mainLayout.bottom)
                    }
                    .height(150.dp)
            )

            //* TODO problem is with request!! */
            ForecastLayout(
                forecastData = forecastModel, modifier = Modifier
                    .constrainAs(forecastLayout) {
                        top.linkTo(conditionLayout.bottom)
                    }
                    .height(200.dp)
            )
        } else getEmptyData().cityName
    }
}


fun getLocation() {

}




