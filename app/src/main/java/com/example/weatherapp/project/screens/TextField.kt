package com.example.weatherapp.project.screens


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.project.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun MainTextField(
    modifier: Modifier,
    coroutineScope: CoroutineScope,
    mainViewModel: MainViewModel,
) {

    var newChar by remember { mutableStateOf("") }

    TextField(
        value = newChar,
        onValueChange = {
            newChar = it
        },
        modifier = modifier,
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
            cursorColor = colorResource(R.color.black),
            focusedIndicatorColor = colorResource(R.color.transparent),
            unfocusedIndicatorColor = colorResource(R.color.transparent),
            backgroundColor = colorResource(R.color.semi_transparent)
        ),
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                isProgressBarVisible.value = true
                coroutineScope.launch {
                    mainViewModel.getWeatherResponse(newChar).collect {
                        weatherModel = it
                        newChar = ""
                        isSearched.value = true
                    }
                }
            }
        )
    )

}