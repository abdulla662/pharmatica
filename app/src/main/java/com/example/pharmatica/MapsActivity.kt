package com.example.pharmatica

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MapsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val uri = intent.data

        if (uri != null) {
            val latitude = uri.getQueryParameter("lat")
            val longitude = uri.getQueryParameter("lon")
            val query = uri.getQueryParameter("q")

            if (latitude != null && longitude != null && query != null) {
                // Open Google Maps with the provided latitude, longitude, and query
                val mapUri = Uri.parse("geo:$latitude,$longitude?q=$query")
                val mapIntent = Intent(Intent.ACTION_VIEW, mapUri)
                startActivity(mapIntent)
            }
        }

        finish()
    }
}
