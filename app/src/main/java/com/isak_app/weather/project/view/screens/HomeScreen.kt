package com.isak_app.weather.project.view.screens


import android.content.Intent
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.isak_app.weather.R
import com.isak_app.weather.project.main.BaseApplication
import com.isak_app.weather.project.models.forecast.ForecastResponse
import com.isak_app.weather.project.models.location_images.ImageResponse
import com.isak_app.weather.project.models.weather.WeatherResponse
import com.isak_app.weather.project.utils.Constants.IMAGE_CLIENT_ID
import com.isak_app.weather.project.view.composables.FloatingButtons
import com.isak_app.weather.project.view.composables.ProgressBar
import com.isak_app.weather.project.view.composables.SearchTextField
import com.isak_app.weather.project.view.composables.toggleProgressBar
import com.isak_app.weather.project.view.layouts.ForecastLayout
import com.isak_app.weather.project.view.layouts.ImageLayout
import com.isak_app.weather.project.view.layouts.TopWeatherData
import com.isak_app.weather.project.view.layouts.WindAndHumidity
import com.isak_app.weather.project.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

var isCurrentLocation = mutableStateOf(true)
var isSearched = mutableStateOf(false)
var isProgressBarVisible = mutableStateOf(false)

var weatherModel = WeatherResponse.getMockObj()
var forecastModel = ForecastResponse.getForecastMockObj()
var imagesList = ImageResponse.getMockObj()


@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    searchGoogleMapsResult: ActivityResultLauncher<Intent>,
    searchIntent: Intent
) {
    val listState = rememberLazyListState()
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
        val (progressbar, searchET, location, mainLayout, conditionLayout, forecastLayout, imageLayout) = createRefs()
Image(
    modifier = Modifier.fillMaxSize(),
    painter = painterResource(id = R.drawable.background),
    contentDescription ="wallpaper",
    contentScale = ContentScale.FillHeight
)
        SearchTextField(
            modifier = Modifier
                .zIndex(2f)
                .padding(4.dp)
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
            isCurrentLocation.value = false

            ImageLayout(images = imagesList, modifier = Modifier
                .constrainAs(imageLayout){
                    top.linkTo(searchET.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(forecastLayout.top)
                }

            )

            TopWeatherData(
                coroutineScope=coroutineScope,
                listState=listState,
                weatherModel = weatherModel, modifier = Modifier
                    .constrainAs(mainLayout) {
                        top.linkTo(searchET.bottom)
                    }
                    .padding(top = 50.dp)

            )

            WindAndHumidity(
                weatherData = weatherModel, modifier = Modifier
                    .constrainAs(conditionLayout) {
                        top.linkTo(mainLayout.bottom)
                    }
                    .padding(vertical = 20.dp)
            )

            ForecastLayout(
                forecastData = forecastModel, modifier = Modifier
                    .constrainAs(forecastLayout) {
                        top.linkTo(conditionLayout.bottom)
                        bottom.linkTo(parent.bottom)
                    }
            )

        }

        FloatingButtons(
            modifier = Modifier
                .constrainAs(location) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .size(80.dp)
                .padding(16.dp),
            floatingButtonModifier = Modifier,
            imageModifier = Modifier.size(24.dp),
            shape = CircleShape,
            elevation = 20.dp,
            backgroundColor = colorResource(id = R.color.white),
            onClick = {
                isCurrentLocation.value = true
                toggleProgressBar(true)
                if (mainViewModel.getCityNameLiveData().value?.isEmpty() == true) {
                    Toast
                        .makeText(
                            BaseApplication.getInstance().applicationContext,
                            "No Location permission granted!",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                    toggleProgressBar()
                } else {
                    search(
                        coroutineScope,
                        mainViewModel,
                        mainViewModel.getCityNameLiveData().value ?: return@FloatingButtons
                    )
                }
            },
            contentDescription = "location",
            painter = if (!isCurrentLocation.value) painterResource(R.drawable.gps_location) else painterResource(
                R.drawable.gps_no_location
            )
        )


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

                        mainViewModel.getImages(cityName, IMAGE_CLIENT_ID).collect {
                            imagesList = it
                            isSearched.value = true
                            isCurrentLocation.value = false
                        }
                    }
            }
    }
}