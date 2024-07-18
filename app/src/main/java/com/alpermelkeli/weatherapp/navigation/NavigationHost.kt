package com.alpermelkeli.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alpermelkeli.weatherapp.page.ForecastReport
import com.alpermelkeli.weatherapp.page.HomePage
import com.alpermelkeli.weatherapp.page.SelectLocation
import com.alpermelkeli.weatherapp.viewmodel.WeatherViewModel

@Composable
fun NavigationHost(navController: NavHostController, weatherViewModel: WeatherViewModel){
    NavHost(navController = navController, startDestination = "HomePage") {
        composable("HomePage") { HomePage(navController = navController, weatherViewModel) }
        composable("ForecastReport"){ ForecastReport(weatherViewModel = weatherViewModel,navController) }
        composable("SelectLocation"){ SelectLocation(weatherViewModel, navController = navController) }
    }
}