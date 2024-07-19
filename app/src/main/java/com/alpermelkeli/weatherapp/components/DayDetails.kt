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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpermelkeli.weatherapp.R
import com.alpermelkeli.weatherapp.model.DailyWeather
import com.alpermelkeli.weatherapp.repository.millisToDate
import com.alpermelkeli.weatherapp.ui.theme.containerBackground

@Composable
fun DayDetails(dailyWeather: DailyWeather){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(brush = containerBackground, shape = ShapeDefaults.Large, alpha = 0.3f),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "Today, "+millisToDate(System.currentTimeMillis()) ,
            color = Color.White,
            modifier = Modifier.padding(top = 20.dp))

        Text(text = dailyWeather.degree.toString() + "°",
            fontSize = 70.sp,
            modifier = Modifier.padding(start = 25.dp),
            color = Color.White)
        Text(text = dailyWeather.situation,
            fontSize = 15.sp,
            color = Color.White)
        Column(modifier = Modifier.fillMaxWidth(0.5f), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.wind), contentDescription = "Wind",
                    tint = Color.White)
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = "Wind", color = Color.White)
                Spacer(modifier = Modifier.width(15.dp))
                Spacer(modifier = Modifier
                    .background(Color.White)
                    .height(15.dp)
                    .width(1.dp))
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = dailyWeather.wind.toString()+" km/h",
                    color = Color.White,)
            }
            Spacer(modifier = Modifier.height(7.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.humidity), contentDescription = "Wind",
                    tint = Color.White)
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Hum", color = Color.White)
                Spacer(modifier = Modifier
                    .background(Color.White)
                    .height(15.dp)
                    .width(1.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = dailyWeather.hum.toString()+" %",
                    color = Color.White,)
            }
        }

        Spacer(modifier = Modifier.height(1.dp))




    }


}



@Preview
@Composable
fun DayDetailsPreview(){
    DayDetails(dailyWeather = DailyWeather(29, "Cloudy", 10, 27,
        "10n"))
}
