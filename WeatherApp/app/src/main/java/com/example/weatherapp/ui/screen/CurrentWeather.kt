package com.example.weatherapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

@Composable
fun CurrentWeather() {
    // Main Container
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){

        Row (
            modifier = Modifier
                .fillMaxWidth()
//                .offset(y= (-300).dp)
                .background(color = Color(0xFFE3EDEF))
            ,
//            horizontalArrangement = Arrangement.Center
        ){
            WeatherCondition()
        }
    }
}

@Composable
fun WeatherCondition() {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                //outline for visual understanding
//                .background(color = Color(0xFFff4500))
            ,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            // Image changes depending on th weather
            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(R.drawable.cloud_sun_svgrepo_com),
                contentDescription = ""
            )
            Text("Overcast")                                   // Weather Conditon
            Text("6" + "°" + "C", fontSize = 25.sp)            // Actual tempreture
            Text("Feels like" + " 7°C", fontSize = 10.sp)      // wind tempreture
            Text("Wind " + "SW " + "18kph", fontSize = 10.sp)  // wind speeds
        }

}
@Preview(showBackground = true)
@Composable
fun CurrentWeatherPreview() {
    CurrentWeather()
}







