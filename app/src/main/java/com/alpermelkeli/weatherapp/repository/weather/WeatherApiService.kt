package com.alpermelkeli.weatherapp.repository.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): Call<WeatherResponse>
    @GET("data/2.5/forecast")
    fun getHourlyWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): Call<HourlyWeatherResponse>
}
