package com.alpermelkeli.weatherapp.repository

import com.alpermelkeli.weatherapp.model.Location
import com.alpermelkeli.weatherapp.model.DailyWeather
import com.alpermelkeli.weatherapp.model.HourlyWeather
import com.alpermelkeli.weatherapp.repository.location.LocationStorage
import com.alpermelkeli.weatherapp.repository.weather.HourlyWeatherResponse
import com.alpermelkeli.weatherapp.repository.weather.WeatherApiService
import com.alpermelkeli.weatherapp.repository.weather.WeatherResponse
import com.alpermelkeli.weatherapp.viewmodel.WeatherViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WeatherRepository {
    private val weatherApiService = RetrofitClient.weatherApiService
    private val apiKey = "991f636434365599f64c110ed496cee7"

    suspend fun getDailyWeather(callback: (DailyWeather?) -> Unit) {
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
                            val dailyWeather = DailyWeather(
                                degree = (weatherResponse.main.temp - 273.15).toInt(), // Kelvin to Celsius
                                situation = weatherResponse.weather[0].description,
                                wind = weatherResponse.wind.speed.toInt(),
                                hum = weatherResponse.main.humidity
                            )
                            callback(dailyWeather)
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

    suspend fun getHourlyWeather(callback: (List<HourlyWeather>?) -> Unit) {
        val location = withContext(Dispatchers.IO) {
            LocationStorage.getLocation()
        }
        location?.let {
            val call = weatherApiService.getHourlyWeather(it.city, apiKey)
            call.enqueue(object : Callback<HourlyWeatherResponse> {
                override fun onResponse(call: Call<HourlyWeatherResponse>, response: Response<HourlyWeatherResponse>) {
                    if (response.isSuccessful) {
                        val hourlyWeatherResponse = response.body()
                        if (hourlyWeatherResponse != null) {
                            val hourlyWeatherList = hourlyWeatherResponse.list.map { item ->
                                val timeString = item.dt_txt.split(" ")[1]
                                val formattedTime = formatTime(timeString)
                                HourlyWeather(
                                    degree = (item.main.temp - 273.15).toInt(), // Kelvin to Celsius
                                    situation = item.weather[0].description,
                                    hour = formattedTime
                                )
                            }
                            callback(hourlyWeatherList)
                        } else {
                            callback(null)
                        }
                    } else {
                        callback(null)
                    }
                }

                override fun onFailure(call: Call<HourlyWeatherResponse>, t: Throwable) {
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