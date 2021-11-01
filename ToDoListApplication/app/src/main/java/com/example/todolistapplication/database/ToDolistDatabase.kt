package com.example.todolistapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolistapplication.database.model.ToDolistModel

@Database(entities = [ToDolistModel::class], version = 2)
abstract class ToDolistDatabase: RoomDatabase() {
    abstract fun toDolistDao():ToDolistDao
}