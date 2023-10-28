package com.itzik_app.weatherapp.project.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

fun getDayOfWeek(jsonDate: String): String {
    var dayOfWeek: String
    val date = simpleDateFormat.parse(jsonDate)
    val calendar = Calendar.getInstance()
    val today = calendar[Calendar.DAY_OF_WEEK]

    val tomorrow = if (today == 7) {
        1
    } else today + 1

    val givenDate = date?.day?.plus(1)
    val dateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    dayOfWeek = date?.let { dateFormat.format(it) }.toString()
    if (givenDate == today) {
        dayOfWeek = "Today"
    } else if (givenDate == tomorrow) {
        dayOfWeek = "Tomorrow"
    }
    return dayOfWeek
}


fun getDate(jsonDate: String): String {
    val dateOfMonth: String
    val date = simpleDateFormat.parse(jsonDate)
    val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
    dateOfMonth = date?.let { dateFormat.format(it) }.toString()
    return dateOfMonth
}

fun getHourOfDay(jsonDate: String): String {
    val hour: String
    val date = simpleDateFormat.parse(jsonDate)
    val dateFormat: DateFormat = SimpleDateFormat("H:mm", Locale.getDefault())
    hour = date?.let { dateFormat.format(it) }.toString()
    return hour
}
