package com.alpermelkeli.weatherapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpermelkeli.weatherapp.model.Weather
import com.alpermelkeli.weatherapp.ui.theme.containerBackground

@Composable
fun DayDetails(weather: Weather){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(brush = containerBackground, shape = ShapeDefaults.Large, alpha = 0.3f),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "Today, 17 July",
            color = Color.White,
            modifier = Modifier.padding(top = 20.dp))

        Text(text = weather.degree.toString() + "°",
            fontSize = 70.sp,
            modifier = Modifier.padding(start = 25.dp),
            color = Color.White)
        Text(text = weather.situation,
            fontSize = 15.sp,
            color = Color.White)
        Column {

        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Face, contentDescription = "Wind",
                tint = Color.White)
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = "Wind", color = Color.White)
            Spacer(modifier = Modifier.width(15.dp))
            Spacer(modifier = Modifier
                .background(Color.White)
                .height(15.dp)
                .width(1.dp))
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = weather.wind.toString()+" km/h",
                color = Color.White,)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Face, contentDescription = "Wind",
                tint = Color.White)
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = "Hum", color = Color.White)
            Spacer(modifier = Modifier.width(15.dp))
            Spacer(modifier = Modifier
                .background(Color.White)
                .height(15.dp)
                .width(1.dp))
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = weather.hum.toString()+" %",
                color = Color.White,)
        }
        Spacer(modifier = Modifier.height(5.dp))



    }


}
@Preview
@Composable
fun DayDetailsPreview(){
    DayDetails(weather = Weather(29, "Cloudy", 10, 27))
}