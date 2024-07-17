package com.alpermelkeli.weatherapp

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alpermelkeli.weatherapp.components.DayDetails
import com.alpermelkeli.weatherapp.components.TopBar
import com.alpermelkeli.weatherapp.ui.theme.background
import com.alpermelkeli.weatherapp.viewmodel.WeatherViewModel

@Composable
fun HomePage(navController: NavHostController, weatherViewModel: WeatherViewModel){
    LaunchedEffect(Unit) {
        weatherViewModel.getDayWeather("Yozgat")
    }
    val weatherState by weatherViewModel.dayWeather.observeAsState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(brush = background)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {

            TopBar {
                navController.navigate("SelectLocation")
            }
            


            weatherState?.let {
                Image(painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.cloudy_vector)),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp))
                DayDetails(weather = it)
            }

            Button(onClick = { navController.navigate("ForecastReport") },
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp),
                colors = ButtonDefaults.outlinedButtonColors().copy(containerColor = Color.White),
                shape = ShapeDefaults.Medium
            )
            {
                Text(text = "Forecast report", color = Color.Black)
            }
            Spacer(modifier = Modifier.height(10.dp))

        }


    }

}
@Preview(showBackground = true)
@Composable
fun Test(){
    val navController = rememberNavController()
    val weatherViewModel = WeatherViewModel()
    HomePage(navController = navController, weatherViewModel = weatherViewModel)
}