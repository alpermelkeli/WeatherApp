package com.alpermelkeli.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alpermelkeli.weatherapp.model.Weather
import com.alpermelkeli.weatherapp.repository.WeatherRepository

class WeatherViewModel:ViewModel() {

    private val weatherRepository = WeatherRepository()

    private val _dayWeather:MutableLiveData<Weather> = MutableLiveData()

    val dayWeather:LiveData<Weather>get() =_dayWeather




    fun getDayWeather(city:String){
        weatherRepository.getWeather(
            callback = {_dayWeather.value = it},
            city = city)
    }
}