package com.alpermelkeli.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.alpermelkeli.weatherapp.viewmodel.WeatherViewModel

@Composable
fun WeatherApp(){
    val weatherViewModel = WeatherViewModel()
    val navController = rememberNavController()
    NavigationHost(navController = navController,weatherViewModel = weatherViewModel)
}