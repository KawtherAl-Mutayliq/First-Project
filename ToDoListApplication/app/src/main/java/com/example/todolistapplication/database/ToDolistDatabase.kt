package com.example.todolistapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolistapplication.database.model.ToDolistModel

// room database contains one table

@Database(entities = [ToDolistModel::class], version = 3)
abstract class ToDolistDatabase: RoomDatabase() {

    // dao interface as a return type
    abstract fun toDolistDao():ToDolistDao
}