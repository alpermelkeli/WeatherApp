package com.alpermelkeli.weatherapp.model

data class DailyWeather(
    val degree:Int,
    val situation:String,
    val wind:Int,
    val hum:Int,
    val icon:String
)
