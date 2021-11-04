package com.example.todolistapplication.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ToDolistModel(
    var title: String,
    var type: String,
    var description: String,
    var datte: String,
    var time:String,
    var dateCreation:String,
    var checkBox: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0)
