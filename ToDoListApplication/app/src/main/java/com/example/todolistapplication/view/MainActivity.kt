package com.example.todolistapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolistapplication.R
import com.example.todolistapplication.repository.ToDolistRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        ToDolistRepository.init(this)


    }
}