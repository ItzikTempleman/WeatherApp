package com.example.weatherapp.project.screens

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun SymbolIcon(onClick: () -> Unit, modifier: Modifier, contentDescription: String?, painter: Painter, tint: Color) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(contentDescription = contentDescription, painter = painter, tint = tint)
    }
}
