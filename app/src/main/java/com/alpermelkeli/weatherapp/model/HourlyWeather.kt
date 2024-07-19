package com.alpermelkeli.weatherapp.model

data class HourlyWeather(
    val degree:Int,
    val situation:String,
    val hour:String,
    val icon:String
)
