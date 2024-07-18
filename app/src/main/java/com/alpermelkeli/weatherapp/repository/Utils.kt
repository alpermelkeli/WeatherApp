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
fun getImageVectorIdByName(situation:String):Int{
    when(situation){
        "clear sky" ->return R.drawable.clear_sky
        "broken clouds" -> return R.drawable.broken_clouds
        "few clouds" -> return R.drawable.few_clouds
        "mist","smoke","haze","sand/dust whirls","fog","sand","dust","volcanic ash","squalls","tornado" -> return R.drawable.mist
        "rain","light rain","moderate rain","heavy intensity rain","very heavy rain","extreme rain","freezing rain","light intensity shower rain","shower rain","heavy intensity shower rain","ragged shower rain" -> return R.drawable.rain
        "scattered clouds","overcast clouds" -> return R.drawable.scattered_clouds
        "shower rain" -> return R.drawable.shower_rain
        "snow","light snow","heavy snow","sleet","light shower sleet","shower sleet","light rain and snow","rain and snow","light shower snow","shower snow","heavy shower snow" ->return R.drawable.snow
        "thunderstorm" ->return R.drawable.thunderstorm
    }
    return R.drawable.ic_launcher_foreground
}