package com.example.weatherapp

import android.util.Log
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

    fun fetchWeatherForLocation(location: String) {
        // Don't fetch if the location is empty
        if (location.isBlank()) return

        viewModelScope.launch {
            try {
                // Initialize weather class
                val weatherData = weatherService.getWeather(location)
                // Assigns weather class to mutable state
                _weather.value = weatherData
            } catch (e: Exception) {
                Log.i("TESTING", "Error fetching weather", e)
            }
        }
    }
}

