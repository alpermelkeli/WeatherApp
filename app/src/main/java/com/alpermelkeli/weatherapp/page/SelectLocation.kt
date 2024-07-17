package com.alpermelkeli.weatherapp.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.alpermelkeli.weatherapp.viewmodel.WeatherViewModel

@Composable
fun SelectLocation(weatherViewModel: WeatherViewModel){
    var cityText by remember{ mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()){
        Column {
            TextField(value = cityText, onValueChange = {cityText = it})
            Button(onClick = { weatherViewModel.updateDayWeather(cityText) }) {
                Text(text = "Select")
            }
        }
    }
}