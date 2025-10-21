package com.example.weatherapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.models.Forecast
import java.time.LocalDate
import java.time.format.DateTimeFormatter


//Custom Date class to avoid repetition


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyForecast(forecast: List<Forecast>) {

    //Main view
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAF9F9))
    )
    {

        //Main content goes here

        //For each loop to reneder days of the week from forecast list
        forecast.forEach { item ->
            Box(
                contentAlignment = Alignment.Center
            ) {
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Forecast(f: Forecast?) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier
            .clip(shape = Shapes().extraLarge)
            .fillMaxWidth()
            .background(color = Color(0xFFDCDCDC))
//            .padding(vertical = 10.dp)
            .border(
                width = 1.dp,
                shape = Shapes().extraLarge,
                color = Color(0x00FF4500)
                )
    )
    {

        //todo: Add backgroundd image for foreast

        if (f != null) {
            //Display date is string format
            Text(formatDate(f.date, "E, d MMMM"))

            //Image to visualize weather conditions for day
            Image(
                painter = rememberAsyncImagePainter("https:${f.day.condition.icon}"),
                modifier = Modifier.size(70.dp),
                contentDescription = "Weather Status"
            )

            // Description for the weather
            Text("${f.day.tempHigh}°C High ${f.day.tempLow}°C Low")

            //More insight on weather conditions

            //Logic for precipitation
            if (f.day.preciAmount == 0.0) {
                Text(
                    "${f.day.condition.text}. Maximum winds ${f.day.windSpeed}kph. Humidity ${f.day.humidity}%",
                    textAlign = TextAlign.Center
                )
            }
            // Fall back if none
            else {
                Text(
                    "${f.day.condition.text}. Chance of rain is ${f.day.preciChance}%. Amount ${f.day.preciAmount}mm. Maximum winds ${f.day.windSpeed}kph. Humidity ${f.day.humidity}%",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(date: String, format: String): String {

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val newdate = LocalDate.parse(date, formatter)

    return(newdate.format(DateTimeFormatter.ofPattern(format)))

}