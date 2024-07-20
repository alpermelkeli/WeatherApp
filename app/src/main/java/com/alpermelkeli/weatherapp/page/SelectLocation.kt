package com.alpermelkeli.weatherapp.page

import android.widget.Space
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alpermelkeli.weatherapp.repository.fetchCitySuggestions
import com.alpermelkeli.weatherapp.repository.location.LocationStorage
import com.alpermelkeli.weatherapp.ui.theme.Blue1
import com.alpermelkeli.weatherapp.ui.theme.Blue2
import com.alpermelkeli.weatherapp.ui.theme.Grey1
import com.alpermelkeli.weatherapp.ui.theme.GreyContainer
import com.alpermelkeli.weatherapp.ui.theme.background
import com.alpermelkeli.weatherapp.viewmodel.WeatherViewModel

@Composable
fun SelectLocation(weatherViewModel: WeatherViewModel,navController: NavHostController){

    var cityText by remember{ mutableStateOf(LocationStorage.getLocation()?.city ?: "") }
    var suggestions by remember { mutableStateOf(listOf<String>()) }
    var showSuggestions by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(background),
        contentAlignment = Alignment.TopCenter){
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
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
                onValueChange = {
                    cityText = it
                    suggestions = fetchCitySuggestions(it)
                    showSuggestions = suggestions.isNotEmpty()
                },
                leadingIcon = { Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black, modifier = Modifier
                    .size(20.dp)
                    .clickable { navController.popBackStack() })},
                shape = ShapeDefaults.Medium,
                colors = TextFieldDefaults.colors().copy(unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedContainerColor = GreyContainer, focusedContainerColor = GreyContainer, cursorColor = Blue2, focusedTextColor = Color.Black, unfocusedTextColor = Color.Black),
                modifier = Modifier.fillMaxWidth(0.8f)
                )

            if (showSuggestions) {
                Spacer(modifier = Modifier.height(15.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(200.dp)
                        .background(Color.White)
                ) {
                    LazyColumn {
                        items(suggestions){
                                suggestion ->
                            Text(
                                text = suggestion,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        cityText = suggestion
                                        showSuggestions = false
                                    }
                                    .padding(16.dp),
                            )
                        }

                    }
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            Button(onClick = { weatherViewModel.updateDayWeather(cityText) }, colors = ButtonDefaults.buttonColors().copy(containerColor = GreyContainer, contentColor = Color.White)) {
                Text(text = "Select")
            }
        }
    }
}
