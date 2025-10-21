package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.Condition
import com.example.weatherapp.models.Current
import com.example.weatherapp.models.Forecast


import com.example.weatherapp.models.Weather

import com.example.weatherapp.ui.screen.Date
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import com.example.weatherapp.services.WeatherService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    private val _weather = MutableStateFlow<Weather?>(null)
    val weather = _weather.asStateFlow()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherService: WeatherService  =retrofit.create(WeatherService::class.java)

    init {

        val today = Date("October", 10, 2025); //initialize date

        viewModelScope.launch {


        //initialize weather class
        var weather = Weather(
            //Current weather
           current = weatherService.getCurrent(),

            //List of forecasts
            forecast = listOf<Forecast>(
                Forecast(
                    date = "",
                    condition = Condition ("Sunny", ""),
                    windSpeed = 15.0,
                    tempLow = 10.0,
                    tempHigh = 8.0,
                    preciAmount = 0.0,
                    preciChance = 0,
                    humidity = 45.0
                ),
                Forecast(
                    date = "",
                    condition = Condition ("Partly Cloudy", ""),
                    windSpeed = 22.0,
                    tempLow = 8.0,
                    tempHigh = 5.0,
                    preciAmount = 40.0,
                    preciChance = 12,
                    humidity = 87.0
                ),
                Forecast(
                    date = "",
                    condition = Condition ("Overcast", ""),
                    windSpeed =  30.0,
                    tempLow = 6.0,
                    tempHigh = 4.0,
                    preciAmount = 40.0,
                    preciChance = 12,
                    humidity = 87.0
                )
            )
        )

        //sets date for each forecast
        weather.forecast?.forEach({
            forecast ->
                forecast.date = Date(
                    today.month,
                    (today.day + weather.forecast.indexOf(forecast)),
                    today.year).toString()

//                forecast.weatherImage.weatherBackgroundRId = R.drawable.drop

            })

        //assigns weather class to mutable state
        _weather.value = weather
    }
}
}