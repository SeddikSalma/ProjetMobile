package com.example.project.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project.R
import com.example.project.utils.ConnectionManager

class NotConnectedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_connected)

        ConnectionManager.isConnected.observe(this) {
            when(it) {
                true -> {
                    val intent = Intent(this, SplashScreen::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                }
                false -> {

                }
            }
        }
    }
}