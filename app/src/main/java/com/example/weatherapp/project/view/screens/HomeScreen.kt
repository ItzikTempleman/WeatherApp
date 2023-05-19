package com.example.weatherapp.project.view.screens


import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherapp.R
import com.example.weatherapp.project.main.BaseApplication
import com.example.weatherapp.project.models.forecast.ForecastResponse
import com.example.weatherapp.project.models.unsplash_location_image.UnsplashImageResponse
import com.example.weatherapp.project.models.weather.WeatherResponse
import com.example.weatherapp.project.utils.Constants.UNSPLASH_CLIENT_ID
import com.example.weatherapp.project.view.ProgressBar
import com.example.weatherapp.project.view.composables.SearchTextField
import com.example.weatherapp.project.view.layouts.DataLayout
import com.example.weatherapp.project.view.toggleProgressBar
import com.example.weatherapp.project.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

var isCurrentLocation = mutableStateOf(true)
var isSearched = mutableStateOf(false)
var weatherModel = WeatherResponse.getMockObj()
var forecastModel = ForecastResponse.getForecastMockObj()
var isProgressBarVisible = mutableStateOf(false)
var imagesList = UnsplashImageResponse.getMockObj()

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    searchGoogleMapsResult: ActivityResultLauncher<Intent>,
    searchIntent: Intent
) {

    val coroutineScope = rememberCoroutineScope()

    toggleProgressBar(true)
    mainViewModel.getCityNameLiveData().observe(LocalLifecycleOwner.current){ cityName ->
        if (cityName.isNotEmpty()){
            search(coroutineScope, mainViewModel, cityName)
        }
    }

    search(coroutineScope, mainViewModel)


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (progressbar, searchET, location, imageLazyRow) = createRefs()



        SearchTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .constrainAs(searchET) {
                    top.linkTo(parent.top)
                },
            coroutineScope = coroutineScope,
            mainViewModel = mainViewModel,
            searchIntent = searchIntent,
            searchGoogleMapsResult = searchGoogleMapsResult
        )

        if (isSearched.value) {
            isCurrentLocation.value=false

            DataLayout(
                weatherModel = weatherModel, modifier = Modifier
                    .constrainAs(imageLazyRow) {
                        top.linkTo(searchET.bottom)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }

        Surface(
            modifier = Modifier
                .constrainAs(location) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .size(80.dp)
                .padding(16.dp),
            shape = CircleShape,
            elevation = 20.dp
        ) {
            FloatingActionButton(
                backgroundColor = colorResource(id = R.color.white),
                modifier = Modifier,
                onClick = {
                    isCurrentLocation.value=true
                    toggleProgressBar(true)
                    if (mainViewModel.getCityNameLiveData().value?.isEmpty() == true){
                        Toast
                            .makeText(BaseApplication.getInstance().applicationContext, "No Location permission granted!", Toast.LENGTH_SHORT)
                            .show()
                        toggleProgressBar()
                    }else{
                        search(coroutineScope, mainViewModel, mainViewModel.getCityNameLiveData().value ?: return@FloatingActionButton)
                    }
                }
            ) {
                Image(
                    painter = if(!isCurrentLocation.value) painterResource(R.drawable.gps_no_location) else painterResource(R.drawable.gps_location) ,
                    contentDescription = "location",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        ProgressBar(
            modifier = Modifier
                .width(44.dp)
                .height(44.dp)
                .constrainAs(progressbar) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            isVisible = isProgressBarVisible.value
        )
    }
}

fun search(
    coroutineScope: CoroutineScope,
    mainViewModel: MainViewModel,
    cityName: String = ""
) {
    coroutineScope.launch {
        mainViewModel.getWeatherResponse(cityName)
            .collect { weatherIt ->
                weatherModel = weatherIt
                mainViewModel.getForecastResponse(
                    weatherIt.coordinates.lat,
                    weatherIt.coordinates.lon
                )
                    .collect { forecastIt ->
                        forecastModel = forecastIt
                        isSearched.value = true
                        mainViewModel.getImagesFromUnsplash(cityName,UNSPLASH_CLIENT_ID).collect{imageIt->
                            imagesList=imageIt
                            Log.d("TAG_IM", "images: $imagesList")
                        }
                    }
            }
    }
}







