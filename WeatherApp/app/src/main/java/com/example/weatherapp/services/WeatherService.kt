package com.example.weatherapp.services

import com.example.weatherapp.models.Current
import com.example.weatherapp.models.Forecast
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("current.json?key=88585df785c74f2a902134735251610&q=Halifax")
    suspend fun getCurrent(): Current

    @GET("forecast.json?key=88585df785c74f2a902134735251610&q=Halifax&days=7&aqi=no&alerts=no")
    suspend fun getForecast(): Forecast
}