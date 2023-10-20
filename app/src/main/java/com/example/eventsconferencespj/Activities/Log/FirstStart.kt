package com.example.eventsconferencespj.Activities.Log

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.WindowManager
import android.widget.Button
import com.example.eventsconferencespj.R

class FirstStart : AppCompatActivity() {
    private var mLastClickTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_start)

        val button = findViewById<Button>(R.id.buttonStart)

        button.setOnClickListener{
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1500) {
                return@setOnClickListener
            }
            mLastClickTime = SystemClock.elapsedRealtime()

            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}