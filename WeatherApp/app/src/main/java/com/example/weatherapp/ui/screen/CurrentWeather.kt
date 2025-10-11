package com.example.weatherapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.models.Current

@Composable
fun CurrentWeather(current: Current?) {

    // Main Container
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(current?.weatherImage!!.weatherBackgroundRId),
                contentDescription = ""
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )
            {
                WeatherCondition(current)
            }

        }
    }
}

@Composable
fun WeatherCondition(cw: Current?) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image changes depending on the weather
        Image(
            modifier = Modifier.size(150.dp), //Todo: Edit transparent space around image
            painter = painterResource(cw?.weatherImage!!.weatherIconRId),
            contentDescription = "Weather Condition"
        )
        Text(text = cw.condition, fontSize = 25.sp)  // Weather Condition

        Text(
            "${cw.temperature.actual}°C", // Actual temperature
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "Feels like " + "${cw.temperature.actual}°C", // wind temperature
            fontSize = 16.sp
        )

        Text(
            "Wind " + "${cw.wind.direction} ${cw.wind.speed} kph", // wind speeds
            fontSize = 16.sp
        )
    }
}

