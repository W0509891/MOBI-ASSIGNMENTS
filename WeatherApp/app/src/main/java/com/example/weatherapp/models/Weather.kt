package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

// ========================Main Classes======================== //
data class Weather(
    val current: Current,
    val forecast: Forecastday,
)


data class Current(
    val condition: Condition,
    @SerializedName("temp_c") val temp: Double,
    @SerializedName("wind_dir") val windDirection: String,
    @SerializedName("wind_kph") val windSpeed: Double,
    @SerializedName("windchill_c") val windTemp: Double,
    @SerializedName("feelslike_c") val feels: Double,
)


data class Forecast(
    var date: String, //Ask - What should be data type
    val day: Day
)

// ========================Helper Classes======================== //


data class Condition(
    val text: String,
    val icon: String,
)

data class Day(
    val condition: Condition,
    @SerializedName("maxtemp_c") val tempHigh: Double,
    @SerializedName("mintemp_c") val tempLow: Double,
    @SerializedName("daily_chance_of_rain") val preciChance: Int,
    @SerializedName("totalprecip_mm") val preciAmount: Double,
    @SerializedName("maxwind_kph") val windSpeed: Double,
    @SerializedName("avghumidity") val humidity: Double,
)


data class Forecastday(
    @SerializedName("forecastday") val forecast: List<Forecast>
)
