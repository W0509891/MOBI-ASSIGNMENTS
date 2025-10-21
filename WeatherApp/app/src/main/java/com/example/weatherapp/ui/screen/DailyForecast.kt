package com.example.weatherapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.models.Forecast

//Custom Date class to avoid repetition
data class Date(var month: String, var day: Int, var year: Int) {
    override fun toString(): String {
        return "$day, $month $year"
    }
}

@Composable
fun DailyForecast(forecast: List<Forecast>) {

    //Main view
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xF2A9A9A9))
    )
    {

        //Main content goes here

        //For each loop to reneder days of the week from forecast list
        forecast.forEach { item ->
            Box(
                contentAlignment = Alignment.Center
            ){
//                Image(
//                    painter = painterResource(item.weatherImage.weatherBackgroundRId),
//                    contentDescription = ""
//                )
                Forecast(item)
            }
            Spacer(
                modifier = Modifier.height(1.dp)
            )
        }
    }
}

@Composable
fun Forecast(f: Forecast) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
//            .background(color = Color(0xFFDCDCDC))
            .padding(vertical = 10.dp)
    )
    {

        //todo: Add backgroundd image for foreast

        //Display date is string format
        Text(f.date)

        //Image to visualize weather conditions for day
        Image(
            painter = rememberAsyncImagePainter(f.condition.icon),
            modifier = Modifier.size(70.dp),
            contentDescription = "Weather Status"
        )

        // Description for the weather
        Text("${f.tempHigh}°C High ${f.tempLow}°C Low")

        //More insight on weather conditions

        //Logic for precipitation
        if (f.preciAmount == 0.0) {
            Text(
                "${f.condition.text}. Maximum winds ${f.windSpeed}kph. Humidity ${f.humidity}%",
                textAlign = TextAlign.Center
            )
        }
        // Fall back if none
        else {
            Text(
                "${f.condition.text}. Chance of rain is ${f.preciChance}%. Amount ${f.preciAmount}mm. Maximum winds ${f.windSpeed}kph. Humidity ${f.humidity}%",
                textAlign = TextAlign.Center
            )
        }
    }
}