package com.alpermelkeli.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpermelkeli.weatherapp.model.DailyWeather
import com.alpermelkeli.weatherapp.model.HourlyWeather
import com.alpermelkeli.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel:ViewModel() {

    private val weatherRepository = WeatherRepository()

    private val _dailyWeather:MutableLiveData<DailyWeather> = MutableLiveData()

    val dailyWeather:LiveData<DailyWeather>get() =_dailyWeather

    private val _hourlyWeather:MutableLiveData<List<HourlyWeather>> = MutableLiveData()

    val hourlyWeather:LiveData<List<HourlyWeather>> get() = _hourlyWeather


    fun getHourlyWeather(){
        viewModelScope.launch {
            weatherRepository.getHourlyWeather {
                _hourlyWeather.postValue(it)
            }
        }
    }

    fun getDayWeather(){
        viewModelScope.launch {
            weatherRepository.getDailyWeather {
                _dailyWeather.postValue(it)
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