package com.example.weatherapp.project.view.composables


import android.util.Log
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.project.view.handleErrors
import com.example.weatherapp.project.view.screens.forecastModel
import com.example.weatherapp.project.view.screens.isSearched
import com.example.weatherapp.project.view.screens.weatherModel
import com.example.weatherapp.project.view.toggleProgressBar
import com.example.weatherapp.project.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun SearchTextField(
    modifier: Modifier,
    coroutineScope: CoroutineScope,
    mainViewModel: MainViewModel
) {

    var newChar by remember { mutableStateOf("") }
    Surface(
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .shadow(elevation = 100.dp, ambientColor = DefaultShadowColor),
        elevation = 50.dp
    ) {
        TextField(
            value = newChar,
            onValueChange = {
                newChar = it
            },
            modifier = modifier,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search), fontWeight = FontWeight.Black
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.search),
                    contentDescription = "search icon"
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = colorResource(R.color.black),
                focusedIndicatorColor = colorResource(R.color.transparent),
                unfocusedIndicatorColor = colorResource(R.color.transparent),
                backgroundColor = colorResource(R.color.transparent)
            ),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    toggleProgressBar(true)
                    coroutineScope.launch {
                        mainViewModel.getWeatherResponse(newChar).handleErrors().collect { weatherIt ->
                            weatherModel = weatherIt
                            mainViewModel.getForecastResponse(
                                weatherIt.coordinates.lat,
                                weatherIt.coordinates.lon
                            ).collect { forecastIt ->
                                forecastModel = forecastIt
                                Log.d("TAG", "forecastModel: $forecastModel")
                                isSearched.value = true
                                newChar = ""
                            }
                        }
                    }
                }
            )
        )
    }
}