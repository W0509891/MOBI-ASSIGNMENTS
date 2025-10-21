package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

// ========================Main Classes======================== //
data class Weather(
    val current: Current,
    val forecast: List<Forecast>,
)


data class Current(
    val temp: Double,
    val condition: Condition,
    @SerializedName("wind_dir") val windDirection: String,
    @SerializedName("wind_kph") val windSpeed: Double,
    @SerializedName("windchill_c") val windTemp: Double,
    @SerializedName("feelslike_c") val feels: Int,
)


data class Forecast(
    var date: String, //Ask - What should be data type
    val condition: Condition,
    @SerializedName("maxtemp_c") val tempHigh: Double,
    @SerializedName("mintemp_c") val tempLow: Double,
    @SerializedName("daily_chance_of_rain") val preciChance: Int,
    @SerializedName("totalprecip_mm") val preciAmount: Double,
    @SerializedName("wind_kph") val windSpeed: Double,
    @SerializedName("avghumidity") val humidity: Double,

    )

// ========================Helper Classes======================== //


data class Condition(
    val text: String,
    val icon: String,
)
