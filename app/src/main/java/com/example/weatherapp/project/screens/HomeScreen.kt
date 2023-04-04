package com.example.weatherapp.project.screens


import android.view.KeyEvent.KEYCODE_ENTER
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.project.main.getEmptyData
import com.example.weatherapp.project.viewmodels.MainViewModel
import kotlinx.coroutines.launch

var isCelsius = mutableStateOf(false)
var weather = getEmptyData()
var temp= 0
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(mainViewModel: MainViewModel) {


    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {


        var newChar by remember { mutableStateOf("") }
        val celsiusBtn = stringResource(id = R.string.celsius)
        val fahrenheitBtn = stringResource(id = R.string.fahrenheit)

        val (
            appName,
            searchET,
            cityNameText,
            temperature,
            celsius,
            dash,
            fahrenheit,
        ) = createRefs()



        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .constrainAs(appName) {

                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            text = stringResource(id = R.string.app_name),
            fontSize = 18.sp
        )

        val (focusRequester) = FocusRequester.createRefs()
        val coroutineScope = rememberCoroutineScope()

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
                    top.linkTo(appName.bottom)
                    end.linkTo(parent.end)
                }

                .onKeyEvent {
                    if (it.nativeKeyEvent.keyCode == KEYCODE_ENTER) {
                        focusRequester.requestFocus()
                        true
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
            } ,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent

            ),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    coroutineScope.launch {
                        mainViewModel.getWeatherResponse(newChar).collect {
                            weather = it
                            temp=it.main.temp.toInt()
                        }
                    }
                }
            )
        )

        Text(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    top = 4.dp,
                    bottom = 4.dp
                )
                .constrainAs(cityNameText) {

                    top.linkTo(searchET.bottom)
                    start.linkTo(parent.start)
                },
            text = weather.cityName,
            fontSize = 28.sp
        )

        Text(
            modifier = Modifier
                .padding(start = 12.dp)
                .constrainAs(temperature) {
                    top.linkTo(cityNameText.bottom)
                    start.linkTo(parent.start)
                },
            fontSize = 48.sp,
            text = if (isCelsius.value) convertFromFahrenheitToCelsius(temp).toString()
            else convertFromCelsiusToFahrenheit(temp).toString()
        )

        ClickableText(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 2.dp)
                .constrainAs(celsius) {
                    top.linkTo(temperature.top)
                    start.linkTo(temperature.end)
                },
            text = AnnotatedString(celsiusBtn),
            style = TextStyle(
                fontSize = 20.sp
            ),
            onClick = {
                isCelsius.value = true
            }
        )

        Text(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 2.dp)
                .constrainAs(dash) {
                    top.linkTo(celsius.top)
                    start.linkTo(celsius.end)
                },
            text = stringResource(id = R.string.dash),
            fontSize = 20.sp)

        ClickableText(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 2.dp)
                .constrainAs(fahrenheit) {
                    top.linkTo(celsius.top)
                    start.linkTo(dash.end)
                },
            style = TextStyle(
                fontSize = 20.sp
            ),
            text = AnnotatedString(fahrenheitBtn),
            onClick = {
                isCelsius.value = false
            }
        )
    }
}


fun convertFromFahrenheitToCelsius(fahrenheit: Int): Int = (fahrenheit - 32 * 5 / 9 )


fun convertFromCelsiusToFahrenheit(celsius: Int): Int = (celsius * 9 / 5 + 32)

