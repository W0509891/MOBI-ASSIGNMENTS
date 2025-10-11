package com.example.weatherapp

import androidx.lifecycle.ViewModel
import com.example.weatherapp.models.Current
import com.example.weatherapp.models.Forecast
import com.example.weatherapp.models.Precipitation
import com.example.weatherapp.models.Temperature
import com.example.weatherapp.models.Weather
import com.example.weatherapp.models.WeatherImage
import com.example.weatherapp.models.Wind
import com.example.weatherapp.ui.screen.Date
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _weather = MutableStateFlow<Weather?>(null)
    val weather = _weather.asStateFlow()

    init {

        val today = Date("October", 10, 2025); //initialize date

        //initialize weather class
        var weather = Weather(
            //Current weather
            Current(
                condition = "Overcast",
                wind = Wind("SW", 99),
                temperature = Temperature(6, 12, 4),
                weatherImage = WeatherImage(
                    weatherIconRId = R.drawable.cloud_overcast,
                    weatherBackgroundRId = R.drawable.cloud_overcast_bg
                ),
                precipitation = Precipitation("", 33, 0),
            ),

            //List of forecasts
            forecast = listOf<Forecast>(
                Forecast(
                    date = "",
                    condition = "Sunny",
                    wind = Wind("NE", 15),
                    temperature = Temperature(10, 18, 7),
                    weatherImage = WeatherImage(
                        weatherIconRId = R.drawable.cloud_sun_alt
                    ),
                    precipitation = Precipitation("None", 0, 0),
                    humidity = 45.0
                ),
                Forecast(
                    date = "",
                    condition = "Partly Cloudy",
                    wind = Wind("E", 22),
                    temperature = Temperature(8, 16, 5),
                    weatherImage = WeatherImage(
                        weatherIconRId = R.drawable.cloud_sun_alt
                    ),
                    precipitation = Precipitation("Light Rain", 10, 5),
                    humidity = 62.0
                ),
                Forecast(
                    date = "",
                    condition = "Overcast",
                    wind = Wind("SW", 30),
                    temperature = Temperature(6, 12, 4),
                    weatherImage = WeatherImage(
                        "overcast.png",
                        R.drawable.cloud_sun_alt
                    ),
                    precipitation = Precipitation("Drizzle", 40, 12),
                    humidity = 87.0
                ),
                Forecast(
                    date = "",
                    condition = "Rain",
                    wind = Wind("W", 40),
                    temperature = Temperature(5, 10, 3),
                    weatherImage = WeatherImage(
                        "./drawable/cloud_rain_alt_1.xml",
                        R.drawable.cloud_rain_alt_1
                    ),
                    precipitation = Precipitation("Moderate Rain", 80, 56),
                    humidity = 92.0
                ),
                Forecast(
                    date = "",
                    condition = "Windy",
                    wind = Wind("NW", 55),
                    temperature = Temperature(7, 14, 5),
                    weatherImage = WeatherImage(
                        "windy.png",
                        R.drawable.cloud_sun_alt
                    ),
                    precipitation = Precipitation("None", 0, 0),
                    humidity = 50.0
                )
            )
        )

        //sets date for each forecast
        weather.forecast?.forEach({
            forecast ->
                forecast?.date = Date(
                    today.month,
                    (today.day + weather.forecast.indexOf(forecast)),
                    today.year).toString()
            })

        //assigns weather class to mutable state
        _weather.value = weather
    }
}