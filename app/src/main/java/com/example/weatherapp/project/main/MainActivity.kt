package com.example.weatherapp.project.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.project.view.screens.HomeScreen
import com.example.weatherapp.project.viewmodels.MainViewModel
import com.example.weatherapp.theme.WeatherAppTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
<<<<<<< HEAD


    private var cityName = ""

=======
    private var cityName = ""
>>>>>>> parent of 804816f (f)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                val mainViewModel = viewModel<MainViewModel>()
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

<<<<<<< HEAD

                HomeScreen(mainViewModel)

                getMyCurrentLocation()
                HomeScreen(mainViewModel)

=======
                getMyCurrentLocation()
                HomeScreen(mainViewModel)
>>>>>>> parent of 804816f (f)
            }
        }
    }


<<<<<<< HEAD

    private fun getMyCurrentLocation(): String {
        var updatedCityName = ""
        if (isPermissionChecked()) {
            if (isMyLocationEnabled()) {

     fun getMyCurrentLocation() {
        if (checkPermission()) {
            if (isMyLocationEnabled()) {
                 //final latitude and longitude code here

=======
    private fun getMyCurrentLocation() {
        if (checkPermission()) {
            if (isMyLocationEnabled()) {
                 //final latitude and longitude code here
>>>>>>> parent of 804816f (f)
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermission()

                }
<<<<<<< HEAD

                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this) {

                    val location = it.result
                    if (location == null) {
                        Toast.makeText(this, "Null location received", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Location received successfully", Toast.LENGTH_SHORT)
                            .show()
                        updatedCityName = getCityName(location.latitude, location.longitude)
                        Log.d("TAG", "updatedCityName: $updatedCityName")

=======
>>>>>>> parent of 804816f (f)
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this){task->
                    val location = task.result
                    if(location==null){
                        Toast.makeText(this, "Null Received", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this, "Get Success", Toast.LENGTH_SHORT).show()
                        getCityName(location.latitude, location.longitude)

                    }
                }

                return updatedCityName
            } else {


                //settings open here
                Toast.makeText(this, "Turn on location", Toast.LENGTH_SHORT).show()
<<<<<<< HEAD

=======
>>>>>>> parent of 804816f (f)
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else requestPermission()

<<<<<<< HEAD
        return updatedCityName
    }

=======
>>>>>>> parent of 804816f (f)
    private fun isMyLocationEnabled(): Boolean {
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
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }

    private fun checkPermission(): Boolean {
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
<<<<<<< HEAD

        val addresses: List<Address> = geoCoder.getFromLocation(lat, long, 10) as List<Address>
        var cityName = ""
        for (adr in addresses) {
            if (!adr.locality.isNullOrEmpty()) {
                cityName = adr.locality
                break
            }
        }

        val address = geoCoder.getFromLocation(lat, long, 1)
        cityName = address?.first()?.adminArea.toString()
        Log.d("TAG","current location: $cityName")

=======
        val address = geoCoder.getFromLocation(lat, long, 1)
        cityName = address?.first()?.adminArea.toString()
        Log.d("TAG","current location: $cityName")
>>>>>>> parent of 804816f (f)
        return cityName
    }
}
