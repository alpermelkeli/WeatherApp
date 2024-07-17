package com.alpermelkeli.weatherapp.repository

data class WeatherResponse(
    val main: Main,
    val weather: List<WeatherDescription>,
    val wind: Wind
)

data class Main(
    val temp: Double,
    val humidity: Int
)

data class WeatherDescription(
    val description: String
)

data class Wind(
    val speed: Double
)