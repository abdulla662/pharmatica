package com.example.pharmatica

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import android.net.Uri
import androidx.core.app.ActivityCompat
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class category : AppCompatActivity() {
    lateinit var button2: Button
    private lateinit var medicaltext: Button
    private lateinit var drugtext: Button
    private lateinit var hospitaltext: Button
    private lateinit var searchtext: Button
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationManager: LocationManager
    private lateinit var tvGpsLocation: TextView
    private val locationPermissionCode = 2
    private lateinit var test: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        medicaltext = findViewById(R.id.medicaltext)
        drugtext = findViewById(R.id.drugtext)
        hospitaltext = findViewById(R.id.hospitaltext)
        searchtext = findViewById(R.id.searchtext)
        medicaltext.setOnClickListener {
         openWebPage("https://altibbi.com/")
        }
        drugtext.setOnClickListener {
            val a=Intent(this@category,results::class.java)
            startActivity(a)        }
        hospitaltext.setOnClickListener {
            getCurrentLocationAndOpenMaps()
        }
        searchtext.setOnClickListener {

            getCurrentLocationAndOpenMapsp()

        }

    }

    private fun openWebPage(url: String) {
        val intent = Intent(this, webview::class.java)
        intent.putExtra("url", url)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("There was a problem loading the page. Please try again later.")
                .setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }
    }


    fun call(view: View) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:123")
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("Permission", "CALL_PHONE permission not granted")
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            return
        }
        Log.d("Calling", "Starting call intent")
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            call(findViewById(R.id.button2)) // Assuming you have a button with this ID
        } else {
            Log.d("Permission", "CALL_PHONE permission denied")
        }
    }

    private fun getCurrentLocationAndOpenMaps() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1000
            )
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                openGoogleMaps(it)
            }
        }.addOnFailureListener {
        }
    }

    private fun openGoogleMaps(location: Location) {
        val uri = Uri.parse("geo:${location.latitude},${location.longitude}?q=hospitals near to me ")
        val gmmIntentUri = Uri.parse("$uri")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)

        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        } else {

        }
    }

    private fun getCurrentLocationAndOpenMapsp() {
        // Check for location permissions
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permissions if not already granted
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1000
            )
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                openGoogleMapsForPharmacies(it)
            }
        }.addOnFailureListener {
            // Handle failure in getting location
            AlertDialog.Builder(this)
                .setTitle("Location Error")
                .setMessage("Unable to obtain location. Please ensure your location settings are enabled and try again.")
                .setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun openGoogleMapsForPharmacies(location: Location) {
        val uri = Uri.parse("geo:${location.latitude},${location.longitude}?q=pharmacies near to me ")
        val gmmIntentUri = Uri.parse("$uri")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)

        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        } else {

        }
    }
}





