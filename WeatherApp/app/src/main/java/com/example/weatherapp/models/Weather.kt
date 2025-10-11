package com.example.weatherapp.models


// ========================Main Classes======================== //
data class Weather (
    val current: Current?,
    val forecast: List<Forecast>?
    )


data class Current(
    val weatherImage: WeatherImage, //Ask - What should be data type
    val condition: String,
    val temperature: Temperature,
    val precipitation: Precipitation,
    val wind: Wind

)


data class Forecast(
    var date: String, //Ask - What should be data type
    val weatherImage: WeatherImage, //Ask - What should be data type
    val temperature: Temperature,
    val condition: String,
    val precipitation: Precipitation,
    val wind: Wind,
    val humidity: Double

)

// ========================Helper Classes======================== //

data class WeatherImage(
    val weatherIcon: String = "",
    val weatherIconRId: Int  = Int.MIN_VALUE,
    val weatherBackground: String = "",
    val weatherBackgroundRId: Int = Int.MIN_VALUE
)
data class Temperature(
    val actual: Int,
    val high: Int,
    val low: Int
)

data class Precipitation(
    val type: String,
    val amount: Int,
    val probability: Int
)

data class Wind(
    val direction: String,
    val speed: Int
)