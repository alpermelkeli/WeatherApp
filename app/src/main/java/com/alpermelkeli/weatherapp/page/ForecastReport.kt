package com.alpermelkeli.weatherapp.page

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alpermelkeli.weatherapp.R
import com.alpermelkeli.weatherapp.components.ForecastTopBar
import com.alpermelkeli.weatherapp.components.HourDetailsItem
import com.alpermelkeli.weatherapp.components.NextForecastItem
import com.alpermelkeli.weatherapp.model.NextForecastWeather
import com.alpermelkeli.weatherapp.repository.getImageVectorIdByIconName
import com.alpermelkeli.weatherapp.repository.millisToDate
import com.alpermelkeli.weatherapp.ui.theme.background
import com.alpermelkeli.weatherapp.viewmodel.WeatherViewModel

@Composable
fun ForecastReport(weatherViewModel: WeatherViewModel,navController: NavHostController) {
    LaunchedEffect(Unit) {
        weatherViewModel.getHourlyWeather()
    }
    val hourlyWeatherState by weatherViewModel.hourlyWeather.observeAsState(emptyList())
    val nextForecastState = hourlyWeatherState
        .filter { it.hour == "12:00" }
        .drop(1)
        .mapIndexed { index, weather ->
            val newDate = millisToDate(System.currentTimeMillis() + (index+1) * 86400000L)
            NextForecastWeather(weather.degree, weather.icon, newDate)
        }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(background))
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(10.dp))
            ForecastTopBar {
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Text(text = "Hourly",
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
                .fillMaxHeight(0.2f)
                )
            {
                items(hourlyWeatherState){
                    HourDetailsItem(it)
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            Row(
                Modifier
                    .fillMaxWidth(0.95f)
                    .height(50.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Next Forecast",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    fontSize = 25.sp
                )
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "DateRange", modifier = Modifier.size(20.dp), tint = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))


            LazyColumn(modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally
                ) {

                items(nextForecastState){
                    NextForecastItem(it)
                }


            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){

                Image(bitmap = ImageBitmap.imageResource(id = R.drawable.openweathericon), contentDescription = "Adveritesement",
                    modifier = Modifier.width(100.dp))
            }



        }

    }
}