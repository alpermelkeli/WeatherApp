package com.alpermelkeli.weatherapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.alpermelkeli.weatherapp.model.HourlyWeather
import com.alpermelkeli.weatherapp.repository.getImageVectorIdByIconName

@Composable
fun HourDetailsItem(hourlyWeather: HourlyWeather){
    Box(modifier = Modifier
        .background(Color.Transparent)
        .height(100.dp)
        .width(50.dp))
    {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween)
        {
            Text(text = hourlyWeather.degree.toString() + "°",
                modifier = Modifier.padding(start = 5.dp),
                color = Color.White)

            Image(painter = rememberVectorPainter(image = ImageVector.vectorResource(id = getImageVectorIdByIconName(hourlyWeather.icon))), contentDescription = "Image",
                modifier = Modifier.size(40.dp))

            Text(text = hourlyWeather.hour,
                color = Color.White)


        }

    }


}