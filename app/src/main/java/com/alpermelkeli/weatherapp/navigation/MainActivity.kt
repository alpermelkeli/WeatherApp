package com.alpermelkeli.weatherapp.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alpermelkeli.weatherapp.repository.location.LocationStorage
import com.alpermelkeli.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        LocationStorage.initialize(this)
        setContent {
            WeatherAppTheme {
                WeatherApp()
            }
        }
    }
}

