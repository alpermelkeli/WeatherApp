package com.alpermelkeli.weatherapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpermelkeli.weatherapp.R
import com.alpermelkeli.weatherapp.model.NextForecastWeather
import com.alpermelkeli.weatherapp.repository.getImageVectorIdByIconName

@Composable
fun NextForecastItem(nextForecastWeather: NextForecastWeather){

    Box(modifier = Modifier
        .background(Color.Transparent)
        .fillMaxWidth(0.95f)
        .height(70.dp),
        contentAlignment = Alignment.Center){

        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Text(text = nextForecastWeather.day,
                color = Color.White)
            Image(imageVector = ImageVector.vectorResource(id = getImageVectorIdByIconName(nextForecastWeather.icon)), contentDescription = "Weather situation",)
            Text(text = nextForecastWeather.degree.toString() + "°",
                color = Color.White)

        }

    }
}