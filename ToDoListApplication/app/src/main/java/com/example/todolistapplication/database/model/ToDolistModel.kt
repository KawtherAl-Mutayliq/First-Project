package com.example.todolistapplication.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDolistModel(val title: String,
                         val type: String,
                         val description: String,
                         val datte: String,
                         val time:String,
                         val checkBox: Boolean = false,
                         @PrimaryKey(autoGenerate = true)
                         val id: Int = 0)
