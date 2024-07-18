package com.alpermelkeli.weatherapp.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alpermelkeli.weatherapp.repository.location.LocationStorage
import com.alpermelkeli.weatherapp.ui.theme.background
import com.alpermelkeli.weatherapp.viewmodel.WeatherViewModel

@Composable
fun SelectLocation(weatherViewModel: WeatherViewModel,navController: NavHostController){

    var cityText by remember{ mutableStateOf(LocationStorage.getLocation()?.city ?: "") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(background),
        contentAlignment = Alignment.TopCenter){
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .background(
                brush = Brush.sweepGradient(listOf(Color.White, Color.White)),
                shape = ShapeDefaults.Large.copy(
                    topEnd = CornerSize(0.dp),
                    topStart = CornerSize(0.dp)
                )
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            TextField(value = cityText,
                onValueChange = {cityText = it},
                leadingIcon = { Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black, modifier = Modifier
                    .size(20.dp)
                    .clickable { navController.popBackStack() })},
                shape = ShapeDefaults.Medium,
                colors = TextFieldDefaults.colors().copy(unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
                )
            Spacer(modifier = Modifier.height(50.dp))

            Button(onClick = { weatherViewModel.updateDayWeather(cityText) }) {
                Text(text = "Select")
            }
        }
    }
}
