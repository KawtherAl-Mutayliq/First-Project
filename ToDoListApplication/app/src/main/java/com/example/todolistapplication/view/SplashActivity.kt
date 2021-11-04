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

        // intent betweem activities
        var intent = Intent(this, MainActivity::class.java)

        // hide the action bar of splash screen
        supportActionBar?.hide()

        // count down timer to display splash screen
        object : CountDownTimer(2000, 1000){
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                startActivity(intent)
            }
        }.start()
    }
}