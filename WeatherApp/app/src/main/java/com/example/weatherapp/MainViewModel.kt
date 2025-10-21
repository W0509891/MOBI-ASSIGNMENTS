package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope



import com.example.weatherapp.models.Weather


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

        viewModelScope.launch {


        //initialize weather class
        var weather = weatherService.getWeather()


        //sets date for each forecast
   /*     weather.forecast?.forEach({
            forecast -> forecast.weatherImage.weatherBackgroundRId = R.drawable.drop

            })*/

        //assigns weather class to mutable state
        _weather.value = weather
    }
}
}

