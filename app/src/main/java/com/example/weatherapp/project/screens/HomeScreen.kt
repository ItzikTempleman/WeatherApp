package com.example.weatherapp.project.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.R


@Composable
fun HomeScreen() {

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        var newChar by remember { mutableStateOf("") }
        val (enterLocationTF, cityNameText, temperature) = createRefs()
        TextField(modifier = Modifier.constrainAs(enterLocationTF) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }, value = newChar,
            onValueChange = {
                newChar = it
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search),
                    fontSize = 12.sp
                )
            }
        )

        Text(
            modifier = Modifier.constrainAs(cityNameText) {

                top.linkTo(enterLocationTF.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "city")

        Text(
            modifier = Modifier.constrainAs(temperature) {

                top.linkTo(cityNameText.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "temperature")
    }
}