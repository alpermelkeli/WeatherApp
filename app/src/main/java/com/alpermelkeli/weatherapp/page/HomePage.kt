package com.alpermelkeli.weatherapp.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.alpermelkeli.weatherapp.R
import com.alpermelkeli.weatherapp.components.DayDetails
import com.alpermelkeli.weatherapp.components.HomeTopBar
import com.alpermelkeli.weatherapp.repository.getImageVectorIdByName
import com.alpermelkeli.weatherapp.repository.location.LocationStorage
import com.alpermelkeli.weatherapp.ui.theme.background
import com.alpermelkeli.weatherapp.viewmodel.WeatherViewModel

@Composable
fun HomePage(navController: NavHostController, weatherViewModel: WeatherViewModel){

    val weatherState by weatherViewModel.dailyWeather.observeAsState()

    var selectedLocation by remember{ mutableStateOf("Select Location") }

    var notificationDialogState by remember{mutableStateOf(false)}

    LaunchedEffect(Unit) {
        weatherViewModel.getDayWeather()
        selectedLocation = LocationStorage.getLocation().let { it?.city }?:"Select Location"
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(brush = background)
    ) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {

            HomeTopBar(selectedLocation, onClickNotification = {notificationDialogState = true}) {
                navController.navigate("SelectLocation")
            }

            weatherState?.let {
                Image(painter = rememberVectorPainter(image = ImageVector.vectorResource(id = getImageVectorIdByName(it.situation))),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp))

                DayDetails(dailyWeather = it)

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
        if(notificationDialogState){
            Dialog(onDismissRequest = {notificationDialogState=false},
                ) {
                Text(text = "Notification Dialog")
            }
        }
    }

}