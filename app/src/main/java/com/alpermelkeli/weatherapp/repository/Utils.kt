package com.alpermelkeli.weatherapp.repository

import com.alpermelkeli.weatherapp.R
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
fun getImageVectorIdByIconName(iconName:String):Int{
    when(iconName){
        "01d","01n" ->return R.drawable.clear_sky
        "04d","04n" -> return R.drawable.broken_clouds
        "02d","02n" -> return R.drawable.few_clouds
        "50d","50n" -> return R.drawable.mist
        "10d","10n"-> return R.drawable.rain
        "03d","03n" -> return R.drawable.scattered_clouds
        "09d","09n" -> return R.drawable.shower_rain
        "13d","13n" ->return R.drawable.snow
        "11d","11n" ->return R.drawable.thunderstorm
    }
    return R.drawable.ic_launcher_foreground
}