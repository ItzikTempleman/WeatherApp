package com.example.weatherapp.project.screens


import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.project.main.getEmptyData
import com.example.weatherapp.project.viewmodels.MainViewModel
import kotlinx.coroutines.launch

var isBtnIsClicked = mutableStateOf(false)


@Composable
fun HomeScreen(mainViewModel: MainViewModel) {


    var weather = getEmptyData()

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val textColor = colorResource(R.color.lighter_grey)
        var newChar by remember { mutableStateOf("") }
        val celsiusBtn = stringResource(id = R.string.celsius)
        val fahrenheitBtn = stringResource(id = R.string.fahrenheit)
        val (
            appName,
            enterLocationTF,
            cityNameText,
            searchBtn,
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



        TextField(value = newChar,
            onValueChange = {
                newChar = it
            },
            modifier = Modifier
                .width(300.dp)
                .padding(start = 12.dp)
                .border(color = colorResource(id = R.color.dark_blue),
                    width = 0.4.dp,
                    shape = RoundedCornerShape(8.dp))
                .constrainAs(enterLocationTF) {
                    start.linkTo(parent.start)
                    top.linkTo(appName.bottom)
                },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = textColor,
                focusedLabelColor = textColor,
                cursorColor = textColor,
                textColor = colorResource(R.color.grey),
                leadingIconColor = Color.Black,
                backgroundColor = colorResource(R.color.light_grey),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,

            shape = RoundedCornerShape(8.dp)
        )

        val composableScope = rememberCoroutineScope()
        SymbolIcon(onClick = {
            composableScope.launch {
                mainViewModel.getWeatherResponse(newChar).collect {
                    weather = it
                    isBtnIsClicked.value = true
                    Log.d("TAG1", "weather= $weather")
                }
            }
        },
            Modifier.constrainAs(searchBtn) {
                    top.linkTo(enterLocationTF.top)
                    bottom.linkTo(enterLocationTF.bottom)
                    start.linkTo(enterLocationTF.end)
                },
            contentDescription = stringResource(id = R.string.search),
            painter = painterResource(R.drawable.search),
            tint = colorResource(id = R.color.black) )

        Text(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    top = 4.dp,
                    bottom = 4.dp
                )
                .constrainAs(cityNameText) {

                    top.linkTo(enterLocationTF.bottom)
                    start.linkTo(parent.start)
                },
            text = if (isBtnIsClicked.value) newChar else weather.cityName,
            fontSize = 28.sp
        )

        Text(
            modifier = Modifier
                .padding(start = 12.dp)
                .constrainAs(temperature) {
                    top.linkTo(cityNameText.bottom)
                    start.linkTo(parent.start)
                },
            text = weather.main.temp.toInt().toString(),
            fontSize = 48.sp)


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
                changeValue(true)
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
                changeValue(false)
            }
        )
    }
    }


fun changeValue(isMetric: Boolean) {
    if (!isMetric) convertFromMetricToImperial()
    else convertFromImperialToMetric()
}

fun convertFromImperialToMetric() {

}

fun convertFromMetricToImperial() {

}

