package com.example.weatherapp.project.view.layouts


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
import com.example.weatherapp.project.models.location_images.ImageResponse

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageLayout(
    modifier: Modifier, images: ImageResponse
) {

    LazyRow(
        modifier = modifier.padding(4.dp) .clip(RoundedCornerShape(12.dp))
    ) {

        items(items = images.results) {
            val locationImage = rememberImagePainter(data = it.resultUrls.imageUrl)
            Column(modifier = modifier.fillMaxSize()) {
                Image(
                    modifier=modifier.width(400.dp).padding(vertical =30.dp, horizontal = 4.dp),
                    contentScale = ContentScale.FillHeight,
                    painter = locationImage,
                    contentDescription = "location_image"
                )
            }

        }
    }
}
