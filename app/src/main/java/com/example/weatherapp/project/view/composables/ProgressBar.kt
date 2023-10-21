package com.example.weatherapp.project.view

import android.annotation.SuppressLint
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.weatherapp.R
import com.example.weatherapp.project.view.screens.isProgressBarVisible


@SuppressLint("SuspiciousIndentation")
@Composable
fun ProgressBar(modifier: Modifier, isVisible:Boolean) {
    if(isVisible)
    CircularProgressIndicator(
        modifier = modifier,
                color= colorResource(id = R.color.black)
    )
}

fun toggleProgressBar(isVisible: Boolean = false) {
    isProgressBarVisible.value = isVisible
}
