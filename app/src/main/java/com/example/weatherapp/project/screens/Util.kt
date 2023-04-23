package com.example.weatherapp.project.screens

import java.util.*


fun convertFromFahrenheitToCelsius(fahrenheit: Double): Double = ((fahrenheit - 32) * 5 / 9)

fun convertFromMilesToKm(mph:Double):Double =mph*1.6


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











































