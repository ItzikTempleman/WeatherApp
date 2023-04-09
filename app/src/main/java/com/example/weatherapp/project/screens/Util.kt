package com.example.weatherapp.project.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.weatherapp.project.main.BaseApplication
import java.util.*


fun convertFromFahrenheitToCelsius(fahrenheit: Double): Double = ((fahrenheit - 32) * 5 / 9)


fun capitalizeDesc(description: String): String {
    return description.substring(0, 1)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() } + description.substring(
        1).toLowerCase()
}


fun getFullCountryName(alpha2CodeName: String): String {
    var countryFullName = ""
    val locale = Locale(countryFullName, alpha2CodeName)
    val fullName = locale.displayCountry
    countryFullName = fullName
    return countryFullName
}




fun loadPicture(url: String): MutableState<Bitmap?> {
    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)
    Glide
        .with(BaseApplication.getInstance()).asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }

        })
    return bitmapState


}



