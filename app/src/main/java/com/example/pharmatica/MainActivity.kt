package com.example.pharmatica

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var videoView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val videoView = findViewById<VideoView>(R.id.videoView)
        val packageName = packageName
        val uri = Uri.parse("android.resource://$packageName/${R.raw.intro1}")
        videoView.setVideoURI(uri)
        videoView.start()
        videoView.setOnCompletionListener {
            val a= Intent(this,webviewpage::class.java)
            startActivity(a)
            finish()
        }

    }
}