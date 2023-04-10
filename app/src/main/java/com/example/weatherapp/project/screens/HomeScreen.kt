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
import com.example.weatherapp.project.viewmodels.MainViewModel

var isSearched = mutableStateOf(false)

var weatherModel = getEmptyData()


var isProgressBarVisible = mutableStateOf(false)


fun stopProgressBar() {
    isProgressBarVisible.value=false
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalCoilApi::class)
@Composable
fun HomeScreen(mainViewModel: MainViewModel) {


    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {


        val (
            progressbar,
            searchET,
            cityNameText,
            countryNameText,
            conditionText,
            temperature,
            percent,
            icon,
            h,
            max,
            l,
            min,
        ) = createRefs()


        val (focusRequester) = FocusRequester.createRefs()
        val coroutineScope = rememberCoroutineScope()

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.wallpaper),
            contentDescription = "background_image",
            contentScale = ContentScale.FillBounds
        )

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

        MainTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
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

        Text(
            modifier = Modifier
                .padding(
                    top = 48.dp,
                )
                .constrainAs(cityNameText) {
                    end.linkTo(parent.end)
                    top.linkTo(searchET.bottom)
                    start.linkTo(parent.start)
                },
            text = weatherModel.cityName,
            fontSize = 48.sp
        )

        Text(
            modifier = Modifier
                .constrainAs(countryNameText) {
                    end.linkTo(parent.end)
                    top.linkTo(cityNameText.bottom)
                    start.linkTo(parent.start)
                },
            text = getFullCountryName(weatherModel.moreInfo.country),

            fontSize = 20.sp
        )

        Text(
            modifier = Modifier
                .constrainAs(temperature) {
                    top.linkTo(countryNameText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            fontSize = 68.sp,
            text = if (isSearched.value) convertFromFahrenheitToCelsius(weatherModel.main.temp).toInt()
                .toString() else getEmptyData().cityName
        )
        Text(
            modifier = Modifier
                .constrainAs(percent) {
                    top.linkTo(temperature.top)
                    start.linkTo(temperature.end)
                },
            fontSize = 28.sp,
            text = if (isSearched.value) "o"
            else getEmptyData().cityName
        )



        if (isSearched.value) {
            val painter = rememberImagePainter(data = weatherModel.weather[0].getImage())
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .constrainAs(icon) {
                        end.linkTo(cityNameText.start)
                        start.linkTo(parent.start)
                        bottom.linkTo(countryNameText.top)
                    },
                painter = painter,
                contentDescription = "icon"
            )
        }



        Text(
            modifier = Modifier
                .constrainAs(conditionText) {
                    top.linkTo(temperature.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            fontSize = 20.sp,
            text = if (isSearched.value) capitalizeDesc(weatherModel.weather[0].description)
            else getEmptyData().cityName,
            fontWeight = FontWeight.Bold
        )


        Text(
            modifier = Modifier
                .constrainAs(h) {
                    top.linkTo(conditionText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                },
            fontSize = 16.sp,
            text = if (isSearched.value) "High: "
            else getEmptyData().cityName
        )
        Text(
            modifier = Modifier
                .constrainAs(max) {
                    top.linkTo(conditionText.bottom)
                    start.linkTo(h.end)

                },
            fontSize = 22.sp,
            text = if (isSearched.value) convertFromFahrenheitToCelsius(weatherModel.main.tempMax).toInt()
                .toString()
            else getEmptyData().cityName
        )

        Text(
            modifier = Modifier
                .constrainAs(l) {
                    top.linkTo(h.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                },
            fontSize = 16.sp,
            text = if (isSearched.value) "Low: "
            else getEmptyData().cityName
        )
        Text(
            modifier = Modifier
                .constrainAs(min) {
                    top.linkTo(h.bottom)
                    start.linkTo(l.end)
                },
            fontSize = 22.sp,
            text = if (isSearched.value && weatherModel.main.tempMin.toInt() != weatherModel.main.tempMax.toInt()) {
                convertFromFahrenheitToCelsius(weatherModel.main.tempMin).toInt().toString()
            } else getEmptyData().cityName
        )
    }
}






