package com.alpermelkeli.weatherapp.repository

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun millisToDate(millis: Long): String {
    val sdf = SimpleDateFormat("dd MMMM", Locale.getDefault())
    val date = Date(millis)
    return sdf.format(date)
}
fun formatTime(time: String): String {
    val inputFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val date = inputFormat.parse(time)
    return outputFormat.format(date)
}