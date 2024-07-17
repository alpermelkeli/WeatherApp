package com.alpermelkeli.weatherapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpermelkeli.weatherapp.model.Weather
import com.alpermelkeli.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel:ViewModel() {

    private val weatherRepository = WeatherRepository()

    private val _dayWeather:MutableLiveData<Weather> = MutableLiveData()

    val dayWeather:LiveData<Weather>get() =_dayWeather




    fun getDayWeather(){
        viewModelScope.launch {
            weatherRepository.getWeather {
                _dayWeather.postValue(it)
            }
        }
    }
    fun updateDayWeather(city:String){
        viewModelScope.launch {
            weatherRepository.updateLocation(city)
            getDayWeather()
        }
    }
}