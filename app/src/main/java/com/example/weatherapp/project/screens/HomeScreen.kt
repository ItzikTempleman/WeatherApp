package com.example.weatherapp.project.screens


import android.view.KeyEvent.KEYCODE_ENTER
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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

@OptIn(ExperimentalComposeUiApi::class)
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
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        val (progressbar, searchET, mainLayout, conditionLayout) = createRefs()

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


            )
        } else getEmptyData().cityName
    }
}


