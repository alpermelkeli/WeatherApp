package com.alpermelkeli.weatherapp.repository

import android.content.Context
import com.alpermelkeli.weatherapp.model.Location
import com.alpermelkeli.weatherapp.model.Weather
import com.alpermelkeli.weatherapp.repository.location.LocationDatabaseHelper
import com.alpermelkeli.weatherapp.repository.location.LocationStorage
import com.alpermelkeli.weatherapp.repository.weather.WeatherApiService
import com.alpermelkeli.weatherapp.repository.weather.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WeatherRepository {
    private val weatherApiService = RetrofitClient.weatherApiService
    private val apiKey = "991f636434365599f64c110ed496cee7"

    suspend fun getWeather(callback: (Weather?) -> Unit) {
        val location = withContext(Dispatchers.IO) {
            LocationStorage.getLocation()
        }
        location?.let {
            println(location.city)
            val call = weatherApiService.getWeather(it.city, apiKey)
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
        } ?: run {
            callback(null)
        }
    }

    suspend fun updateLocation(city: String) {
        withContext(Dispatchers.IO) {
            LocationStorage.insertLocation(Location(city = city))
        }
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