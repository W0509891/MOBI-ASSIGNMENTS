package com.example.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.screen.CurrentWeather
import com.example.weatherapp.ui.screen.DailyForecast
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource



class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                DisplayUI(mainViewModel)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun DisplayUI(mainViewModel: MainViewModel) {


    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    if (permissionState.status.isGranted) {

        // Get Location
        val currentContext = LocalContext.current
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(currentContext)

        if (ContextCompat.checkSelfPermission(
                currentContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        {
            val cancellationTokenSource = CancellationTokenSource()

            Log.i("TESTING", "Requesting location...")

            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token)
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val lat = location.latitude.toString()
                        val lng = location.longitude.toString()
                        Log.i("TESTING", "Success: $lat,$lng")


                        mainViewModel.fetchWeatherForLocation("$lat,$lng")

                    }
                    else {
                        Log.i("TESTING", "Problem encountered: Location returned null")
                    }
                }
        }
    }
    else {

        LaunchedEffect(permissionState){
            permissionState.launchPermissionRequest()
        }
    }


    val weather by mainViewModel.weather.collectAsState()
    val navController = rememberNavController()
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.inverseSurface
                ),
                title = { Text("${weather?.location?.name}, ${weather?.location?.region}") }
            )
        }, //End of topBar

        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            )
            // Navigation items go here
            {
                //Link to Now screen
                NavigationBarItem(
                    label = { Text("Current Weather") },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.cloud_clock),
                            modifier = Modifier.size(30.dp),
                            contentDescription = "Current Weather"
                        )}
                    ,
                    selected = selectedItem == 0,
                    onClick = {
                        selectedItem = 0
                        navController.navigate("CurrentWeather")
                    }
                    ) //End of bottomBar.NavigationBar.NavigationBarItem

                //Link to Forcast Screen
                NavigationBarItem(
                    label = { Text("Forecast") },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.cloud_calender),
                            modifier = Modifier.size(30.dp),
                            contentDescription = "Forecast"
                        )}
                    ,
                    selected = selectedItem == 1,
                    onClick = {
                        selectedItem = 1
                        navController.navigate("DailyForecast")
                    }
                    ) //End of bottomBar.NavigationBar.NavigationBarItem
            } //End of bottomBar.NavigationBar

        } // End of bottomBar
    )

    { innerPadding ->
        // Routing
        NavHost(
            navController =navController,
            startDestination = "CurrentWeather",
            modifier = Modifier.padding(innerPadding)
        ){
            // Displays Current Weather
            composable (route = "CurrentWeather"){
                CurrentWeather(weather?.current)
            }

            composable(route = "DailyForecast") {
                DailyForecast(weather?.forecast!!.forecast)
            }

        }
    }
}

@Composable
fun Greeting(name: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello $name!"
        )


        Text(
            text = "Welcome to my app"
        )
    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun getLocation(): String {
    var coordinates = ""
    // Remember the permission state(asking for Fine location)
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    if (permissionState.status.isGranted) {
        Log.i("TESTING", "Hurray, permission granted!")

        // Get Location
        val currentContext = LocalContext.current
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(currentContext)

        if (ContextCompat.checkSelfPermission(
                currentContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        {
            val cancellationTokenSource = CancellationTokenSource()

            Log.i("TESTING", "Requesting location...")

            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token)
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val lat = location.latitude.toString()
                        val lng = location.longitude.toString()


                        coordinates = "$lat,$lng"
                        Log.i("TESTING", "Success: $coordinates")

                        // call a function, like in View Model, to do something with location...
                    }
                    else {
                        Log.i("TESTING", "Problem encountered: Location returned null")
                    }
                }
        }
    }
    else {
        // Run a side-effect (coroutine) to get permission. The permission popup.
        LaunchedEffect(permissionState){
            permissionState.launchPermissionRequest()
        }
    }

    return coordinates
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {
        Greeting("Android")
    }
}