package com.alpermelkeli.weatherapp.repository.weather

data class HourlyWeatherResponse(
    val list: List<HourlyWeatherItem>
)

data class HourlyWeatherItem(
    val main: MainHourly,
    val weather: List<Weather>,
    val dt_txt: String // Date and time of the forecasted data
)

data class MainHourly(
    val temp: Double
)

data class Weather(
    val description: String,
    val icon:String
)