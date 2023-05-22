package com.example.weatherapp.project.view.layouts

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.weatherapp.project.models.location_images.ImageResponse

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageLayout(
 images:ImageResponse, modifier: Modifier
){
    LazyRow(
        modifier = modifier
    ) {
        items(items = images.results) {
            val locationImage = rememberImagePainter(data = it.resultUrls.imageUrl)
            Column(modifier = Modifier.fillMaxSize()) {
                Log.d("TAG", "image: ${it.resultUrls.imageUrl}")
                Image(
                    modifier = Modifier
                        .width(415.dp)
                        .fillMaxHeight(),
                    contentScale = ContentScale.FillHeight,
                    painter = locationImage,
                    contentDescription = "location_image"
                )
            }
        }
    }
}