package com.example.eventsconferencespj.Activities.Location

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eventsconferencespj.Activities.Home.Home_Screen
import com.example.eventsconferencespj.R

class Location : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val backButton = findViewById<Button>(R.id.backButton)

        backButton.setOnClickListener{
            val intent = Intent(this, Home_Screen::class.java)
            startActivity(intent)
            finish()
        }
    }
}