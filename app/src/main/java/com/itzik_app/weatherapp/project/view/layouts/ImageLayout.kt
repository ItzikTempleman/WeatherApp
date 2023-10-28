package com.itzik_app.weatherapp.project.view.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.itzik_app.weatherapp.project.models.location_images.ImageResponse

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageLayout(
    images: ImageResponse, modifier: Modifier
){
    LazyRow(
        modifier = modifier
    ) {
        items(items = images.results) {
            val locationImage = rememberImagePainter(data = it.resultUrls.imageUrl)
            Column(modifier = modifier.padding(4.dp)) {

                Image(
                    modifier = Modifier
                        .width(240.dp)
                        .height(470.dp).clip(RoundedCornerShape(18.dp)),
                    contentScale = ContentScale.FillHeight,
                    painter = locationImage,
                    contentDescription = "location_image"
                )
            }
        }
    }
}
