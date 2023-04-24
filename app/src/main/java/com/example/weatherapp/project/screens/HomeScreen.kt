package com.example.weatherapp.project.screens


import android.annotation.SuppressLint
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.project.main.getEmptyData
import com.example.weatherapp.project.main.getForecastEmptyData
import com.example.weatherapp.project.viewmodels.MainViewModel


var isSearched = mutableStateOf(false)
var weatherModel = getEmptyData()
var forecastModel = getForecastEmptyData()
var isProgressBarVisible = mutableStateOf(false)

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
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
            .fillMaxWidth()
            .constrainAs(searchET) {
                top.linkTo(parent.top)
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
        

        Surface(
            modifier = Modifier
                .constrainAs(location) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }.size(70.dp)
                .padding(8.dp),
            shape = CircleShape,
            elevation = 20.dp
        ) {
            FloatingActionButton(
                backgroundColor = colorResource(id = R.color.white),
                modifier = Modifier,
                onClick = {
                    getLocation()
                },
            ) {
                Image(
                    painter = painterResource(R.drawable.gps_location),
                    contentDescription = "location",
                    modifier = Modifier.size(30.dp)
                )
            }
        }

        if (isSearched.value) {

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

            ForecastLayout(
                forecastData = forecastModel, modifier = Modifier
                    .constrainAs(forecastLayout) {
                        top.linkTo(conditionLayout.bottom)
                    }
                    .height(150.dp)
            )
        } else getEmptyData().cityName
    }
}


fun getLocation() {

}




