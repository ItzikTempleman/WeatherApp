package com.itzik.weatherapp.project.utils

import com.itzik.weatherapp.project.view.composables.toggleProgressBar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Locale


fun convertFromFahrenheitToCelsius(fahrenheit: Double): Double = ((fahrenheit - 32) * 5 / 9)


fun convertFromKelvinToCelsius(kelvin: Double): Double = kelvin - 273.15

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

fun <T> Flow<T>.handleErrors(): Flow<T> = flow {
    try {
        collect { value -> emit(value) }
    } catch (e: Throwable) {
        toggleProgressBar(false)
    }
}





//TODO THIS IS FOR HILT MODULE LATER:



/**
private val typeConverter = Converters()
 */



/** @Provides
@Singleton
fun provideDb(@ApplicationContext context: Context) = Room.databaseBuilder(
context, AppDatabase::class.java, WEATHER_RESPONSE_DATABASE)
addTypeConverter(typeConverter).
.allowMainThreadQueries()
.fallbackToDestructiveMigration()
.build()

@Provides
@Singleton
fun provideDao(appDatabase: AppDatabase): AppDao = appDatabase.getDao()
 */






































