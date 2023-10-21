package com.itzik.weatherapp.project.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.CoroutineScope

@Composable
fun FloatingButtons(
    coroutineScope: CoroutineScope?=null,
    listState: LazyListState?=null,
    modifier: Modifier,
    floatingButtonModifier:Modifier,
    imageModifier:Modifier,
    shape: RoundedCornerShape,
    elevation: Dp,
    backgroundColor: Color,
    onClick: () -> Unit,
    contentDescription: String,
    painter: Painter
) {
    Surface(
        modifier = modifier,
        shape = shape,
        elevation = elevation
    ) {
        FloatingActionButton(
            backgroundColor = backgroundColor,
            modifier = floatingButtonModifier,
            onClick = onClick
        ) {
            Image(
                modifier = imageModifier,
                contentDescription = contentDescription,
                painter = painter
            )
        }
    }

}
