package com.itzik.weatherapp.project.main

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.itzik.weatherapp.project.utils.Constants.GOOGLE_MAPS_API_KEY
import com.itzik.weatherapp.project.view.screens.HomeScreen
import com.itzik.weatherapp.project.viewmodels.MainViewModel
import com.itzik.weatherapp.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!Places.isInitialized()) {
            Places.initialize(this, GOOGLE_MAPS_API_KEY)
        }


        setContent {
            mainViewModel = viewModel()


            WeatherAppTheme {
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
                val fields = listOf(Place.Field.ID, Place.Field.NAME)
                val searchIntent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields).build(this)

                getMyCurrentLocation()
                HomeScreen(mainViewModel, searchGoogleMapsResult, searchIntent)
            }
        }
    }

    private val searchGoogleMapsResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val intent = it.data
                if (intent != null) {
                    val place = Autocomplete.getPlaceFromIntent(intent)
                    Log.d("TAG", "Place: ${place.name}, ${place.id}")
                }
            } else if (it.resultCode == Activity.RESULT_CANCELED) {
                Log.d("TAG", "User canceled autocomplete")
            }
        }


    private fun getMyCurrentLocation(): String {
        var cityName = ""
        if (wasPermissionAlreadyChecked()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermission()
                }


                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this) {

                    val location = it.result
                    if (location != null) {

                        cityName = getCityName(location.latitude, location.longitude)
                        mainViewModel.setCityNameLiveData(cityName)


                    } else {
                        Toast.makeText(this, "No location received", Toast.LENGTH_SHORT).show()
                    }
                }


            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermission()
        }
        return cityName
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            100
        )
    }


    private fun wasPermissionAlreadyChecked(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(applicationContext, "Granted", Toast.LENGTH_SHORT).show()
            getMyCurrentLocation()
        } else {
            Toast.makeText(applicationContext, "Denied", Toast.LENGTH_SHORT).show()
        }
    }


    private fun getCityName(lat: Double, long: Double): String {
        val geoCoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address> = geoCoder.getFromLocation(lat, long, 10) as List<Address>
        var cityNameFromLocation = ""
        for (adr in addresses) {
            if (!adr.locality.isNullOrEmpty()) {
                cityNameFromLocation = adr.locality
                break
            }
        }
        return cityNameFromLocation
    }

}


