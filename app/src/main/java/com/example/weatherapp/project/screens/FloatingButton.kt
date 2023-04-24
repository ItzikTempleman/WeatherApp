package com.example.weatherapp.project.screens

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape,
    backgroundColor: Color,
    contentColor: Color,
    elevation: FloatingActionButtonElevation
){

    Icon(imageVector = Icons.Filled.Add, contentDescription = "location")
}