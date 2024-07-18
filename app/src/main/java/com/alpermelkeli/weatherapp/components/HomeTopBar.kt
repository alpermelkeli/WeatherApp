package com.alpermelkeli.weatherapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeTopBar(selectedLocation:String, onClickNotification:()->Unit, onClickSelectLocation:()->Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(15.dp))
            Icon(imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = Color.White,
                modifier = Modifier.size(50.dp))
            Spacer(modifier = Modifier.width(15.dp))

            Text(text = selectedLocation,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.clickable { onClickSelectLocation() }
            )
            Spacer(modifier = Modifier.width(15.dp))

            Icon(imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Select Location",
                tint = Color.White,
                modifier = Modifier.clickable { onClickSelectLocation() }
            )
        }
        Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notification",
            modifier = Modifier.padding(end = 15.dp).size(35.dp)
                .clickable { onClickNotification() }, tint = Color.White)
    }

}
