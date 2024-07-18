package com.alpermelkeli.weatherapp.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alpermelkeli.weatherapp.components.ForecastTopBar
import com.alpermelkeli.weatherapp.components.HourDetails
import com.alpermelkeli.weatherapp.repository.millisToDate
import com.alpermelkeli.weatherapp.ui.theme.background
import com.alpermelkeli.weatherapp.viewmodel.WeatherViewModel

@Composable
fun ForecastReport(weatherViewModel: WeatherViewModel,navController: NavHostController) {
    LaunchedEffect(Unit) {
        weatherViewModel.getHourlyWeather()
    }
    val hourlyWeatherState by weatherViewModel.hourlyWeather.observeAsState(emptyList())

    Box(modifier = Modifier
        .fillMaxSize()
        .background(background))
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
            ForecastTopBar {
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Text(text = "Today",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 5.dp)
                )
                Text(text = millisToDate(System.currentTimeMillis()),
                    color = Color.White,
                    modifier = Modifier.padding(end = 1.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600)

            }
            Spacer(modifier = Modifier.height(40.dp))
            LazyRow(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                )
            {
                items(hourlyWeatherState){
                    HourDetails(it)
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }


        }

    }
}