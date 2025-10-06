package com.example.weatherapp.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.models.Weather

data class Date(var month: String, var day: Int, var year: Int){
    override fun toString(): String {
        return "$day, $month $year"
    }

}


@Composable
fun DailyForecast(){

    //TODO: Initalize a date object for dynamic changes
    val dates = arrayListOf<Date>()

    val day = 9;
    val month = "October"
    val year = 2025

    // Loop to "simulate day changes
    for (i in 0..4){
        dates.add(Date(month, day + i, year))
    }


    //Main view
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .windowInsetsTopHeight(WindowInsets(top= 880.dp))
            .background(color = Color(0xFF9378D3))
        ,
//        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(25.dp))

        //Main content goes here
        for (date in dates){
            Forecast(date= date)
            Spacer(modifier = Modifier.height(25.dp))

        }
    }
}

/*
@Composable
fun CurrentDay(){
var myDate = Date("October", 9, 2025)

    Column(
        modifier = Modifier.background(Color(0xff3322ff))
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Display date is string format
        Text(myDate.toString())

        //Image to visualize weather conditions for day
        Image(
            painter = painterResource(R.drawable.cloud_rain_alt_1_svgrepo_com),
            modifier = Modifier.size(70.dp),
            contentDescription = "Weather Status"
        )

        // Description for the weather
        Text("10°C High 3°C Low")

        //More insight on weather conditions
        Text(
            "Heavy rain. Chance of rain 76%. Amount 52.39. Maximum winds 41kph. Humidity 96%",
            textAlign = TextAlign.Center
            )


    }
}

@Composable
fun NextDay(){
    var myDate = Date("October", 10, 2025)

    Column(
        modifier = Modifier.background(Color(0xff3322ff))
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Display date is string format
        Text(myDate.toString())

        //Image to visualize weather conditions for day
        Image(
            painter = painterResource(R.drawable.cloud_rain_alt_1_svgrepo_com),
            modifier = Modifier.size(70.dp),
            contentDescription = "Weather Status"
        )

        // Description for the weather
        Text("10°C High 3°C Low")

        //More insight on weather conditions
        Text(
            "Heavy rain. Chance of rain 76%. Amount 52.39. Maximum winds 41kph. Humidity 96%",
            textAlign = TextAlign.Center
            )


    }
}

@Composable
fun Overmorrow(){
    var myDate = Date("October", 11, 2025)

    Column(
        modifier = Modifier.background(Color(0xff3322ff))
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Display date is string format
        Text(myDate.toString())

        //Image to visualize weather conditions for day
        Image(
            painter = painterResource(R.drawable.cloud_rain_alt_1_svgrepo_com),
            modifier = Modifier.size(70.dp),
            contentDescription = "Weather Status"
        )

        // Description for the weather
        Text("10°C High 3°C Low")

        //More insight on weather conditions
        Text(
            "Heavy rain. Chance of rain 76%. Amount 52.39. Maximum winds 41kph. Humidity 96%",
            textAlign = TextAlign.Center
            )

    }
}

*/

@Composable
fun Forecast(date:Date){
    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .padding(vertical = 10.dp)
            .background(color = Color(0xFFEC4B14))
            .border(BorderStroke(1.dp, Color.Black))

        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Display date is string format
        Text(date.toString())

        //Image to visualize weather conditions for day
        Image(
            painter = painterResource(R.drawable.cloud_rain_alt_1_svgrepo_com),
            modifier = Modifier.size(70.dp),
            contentDescription = "Weather Status"
        )

        // Description for the weather
        Text("10°C High 3°C Low")

        //More insight on weather conditions
        Text(
            "Heavy rain. Chance of rain 76%. Amount 52.39. Maximum winds 41kph. Humidity 96%",
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun DailyForecastPreview() {
    DailyForecast()
}