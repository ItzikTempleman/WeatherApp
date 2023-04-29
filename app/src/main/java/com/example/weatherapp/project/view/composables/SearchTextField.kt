package com.example.weatherapp.project.view.composables


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
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

        TextField(
            value = newChar,
            onValueChange = {
                newChar = it
            },

            modifier = modifier.border(BorderStroke(0.05.dp, colorResource(id = R.color.black))),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search), fontWeight = FontWeight.Black
                )
            },
            visualTransformation = VisualTransformation.None,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.search),
                    contentDescription = "search icon"
                )
            },

            colors = TextFieldDefaults.textFieldColors(
                cursorColor = colorResource(R.color.black),
                textColor = colorResource(R.color.black),
                disabledTextColor = colorResource(R.color.transparent),
                backgroundColor = colorResource(R.color.transparent),
                focusedIndicatorColor = colorResource(R.color.transparent),
                unfocusedIndicatorColor = colorResource(R.color.transparent),
                disabledIndicatorColor = colorResource(R.color.transparent),
                focusedLabelColor=colorResource(R.color.transparent)
            ),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Characters,
                autoCorrect = false,
                imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(

                onSearch = {
                    toggleProgressBar(true)
                    coroutineScope.launch {
                        mainViewModel.getWeatherResponse(newChar).handleErrors()
                            .collect { weatherIt ->
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
