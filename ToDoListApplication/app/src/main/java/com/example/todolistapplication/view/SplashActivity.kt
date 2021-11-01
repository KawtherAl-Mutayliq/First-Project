package com.example.todolistapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.todolistapplication.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var intent = Intent(this, MainActivity::class.java)
        supportActionBar?.hide()

        object : CountDownTimer(2000, 1000){
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                startActivity(intent)

            }
        }.start()
    }
}