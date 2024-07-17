package com.alpermelkeli.weatherapp.repository

import com.alpermelkeli.weatherapp.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {

    private val weatherApiService = RetrofitClient.weatherApiService

    private val apiKey = "991f636434365599f64c110ed496cee7"

    fun getWeather(city: String, callback: (Weather?) -> Unit) {
        val call = weatherApiService.getWeather(city, apiKey)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    if (weatherResponse != null) {
                        val weather = Weather(
                            degree = (weatherResponse.main.temp - 273.15).toInt(), // Kelvin to Celsius
                            situation = weatherResponse.weather[0].description,
                            wind = weatherResponse.wind.speed.toInt(),
                            hum = weatherResponse.main.humidity
                        )
                        callback(weather)
                    } else {
                        callback(null)
                    }
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
}
object RetrofitClient {
    private const val BASE_URL = "https://api.openweathermap.org/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApiService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}