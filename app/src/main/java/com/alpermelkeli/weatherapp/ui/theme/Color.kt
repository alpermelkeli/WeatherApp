package com.alpermelkeli.weatherapp.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Blue1 = Color(0xFF4A91FF)
val Blue2 = Color(0xFF47BFDF)
val Grey1 = Color(0xFF444E72)
val GreyContainer = Color(0xFFD6D6D6)

val background = Brush.linearGradient(
    colors = listOf(Blue2,Blue1),
    start = Offset(0f, 1000f),
    end = Offset(0f, 2000f)
)
val containerBackground = Brush.linearGradient(
    colors = listOf(Color.White, Color.White)
)
