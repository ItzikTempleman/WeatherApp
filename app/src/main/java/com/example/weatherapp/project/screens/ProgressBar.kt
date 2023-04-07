package com.example.weatherapp.project.screens

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R


@Composable
fun GenerateProgressBar(modifier: Modifier,isVisible:Boolean) {
    if(isVisible)
    CircularProgressIndicator(
        modifier = modifier,
                color= colorResource(id = R.color.almost_white)
    )
}