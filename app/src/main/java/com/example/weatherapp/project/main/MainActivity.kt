package com.example.weatherapp.project.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.project.screens.HomeScreen
import com.example.weatherapp.project.viewmodels.MainViewModel
import com.example.weatherapp.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                val mainViewModel = viewModel<MainViewModel>()
                HomeScreen(mainViewModel)
            }
        }
    }
}
