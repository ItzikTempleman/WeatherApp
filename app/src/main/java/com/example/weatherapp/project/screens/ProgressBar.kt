package com.example.weatherapp.project.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R


@SuppressLint("SuspiciousIndentation")
@Composable
fun GenerateProgressBar(modifier: Modifier,isVisible:Boolean) {
    if(isVisible)
    CircularProgressIndicator(
        modifier = modifier,
                color= colorResource(id = R.color.black)
    )
}

fun stopProgressBar() {
    isProgressBarVisible.value = false
}
