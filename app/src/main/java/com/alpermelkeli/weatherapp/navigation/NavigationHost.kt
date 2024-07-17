package com.alpermelkeli.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alpermelkeli.weatherapp.ForecastReport
import com.alpermelkeli.weatherapp.HomePage
import com.alpermelkeli.weatherapp.SelectLocation
import com.alpermelkeli.weatherapp.viewmodel.WeatherViewModel

@Composable
fun NavigationHost(navController: NavHostController, weatherViewModel: WeatherViewModel){
    NavHost(navController = navController, startDestination = "HomePage") {
        composable("HomePage") { HomePage(navController = navController, weatherViewModel) }
        composable("ForecastReport"){ ForecastReport() }
        composable("SelectLocation"){ SelectLocation()}
    }
}