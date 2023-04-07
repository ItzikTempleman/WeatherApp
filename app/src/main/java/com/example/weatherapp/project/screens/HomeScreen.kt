package com.example.weatherapp.project.screens


import android.view.KeyEvent.KEYCODE_ENTER
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.project.main.getEmptyData
import com.example.weatherapp.project.viewmodels.MainViewModel
import kotlinx.coroutines.launch

var isSearched = mutableStateOf(false)

var weather = getEmptyData()


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(mainViewModel: MainViewModel) {


    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {


        var newChar by remember { mutableStateOf("") }


        val (
            searchET,
            cityNameText,
            countryNameText,
            conditionText,
            temperature,
        percent
        ) = createRefs()




        val (focusRequester) = FocusRequester.createRefs()
        val coroutineScope = rememberCoroutineScope()
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.wallpaper),
            contentDescription = "background_image",
            contentScale = ContentScale.FillBounds
        )

        TextField(
            value = newChar,
            onValueChange = {
                newChar = it
            },
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
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search),
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.search),
                    contentDescription = "search icon")
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor=colorResource(R.color.black),
                focusedIndicatorColor = colorResource(R.color.transparent),
                unfocusedIndicatorColor = colorResource(R.color.transparent),
                backgroundColor=colorResource(R.color.semi_transparent)
            ),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    coroutineScope.launch {
                        mainViewModel.getWeatherResponse(newChar).collect {
                            weather = it
                            isSearched.value = true
                        }
                    }
                }
            )
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
            text = weather.cityName,
            fontSize = 28.sp
        )
        Text(
            modifier = Modifier
                .constrainAs(countryNameText) {
                    end.linkTo(parent.end)
                    top.linkTo(cityNameText.bottom)
                    start.linkTo(parent.start)
                },
            text = weather.moreInfo.country,
            fontSize = 20.sp
        )

        Text(
            modifier = Modifier
                .constrainAs(temperature) {
                    top.linkTo(countryNameText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            fontSize = 48.sp,
            text = if (isSearched.value) convertFromFahrenheitToCelsius(weather.main.temp).toInt()
                .toString() else getEmptyData().cityName
        )
        Text(
            modifier = Modifier
                .constrainAs(percent) {
                    top.linkTo(temperature.top)
                    start.linkTo(temperature.end)
                },
            fontSize = 20.sp,
            text = if (isSearched.value) "o"
               else getEmptyData().cityName
        )
        Text(
            modifier = Modifier
                .constrainAs(conditionText) {
                    top.linkTo(temperature.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            fontSize = 20.sp,
            text = if (isSearched.value) weather.weather[0].description
            else getEmptyData().cityName
        )

    }
}

fun convertFromFahrenheitToCelsius(fahrenheit: Double): Double = ((fahrenheit - 32) * 5 / 9)


