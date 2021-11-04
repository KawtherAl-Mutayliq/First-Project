package com.example.todolistapplication.repository

import android.content.Context
import androidx.room.Room
import com.example.todolistapplication.database.ToDolistDao
import com.example.todolistapplication.database.ToDolistDatabase
import com.example.todolistapplication.database.model.ToDolistModel
import java.lang.Exception

// tag for database
private const val DATABASE_NAME = "todolist-database"

class ToDolistRepository(context: Context) {


    // build database

    private val db: ToDolistDatabase = Room.databaseBuilder(context,
        ToDolistDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()

    // get Dao of database

    private val todolistDao = db.toDolistDao()


    // calling all function of dao

    fun getTask() = todolistDao.getTask()

    suspend fun addTask(listModel: ToDolistModel) = todolistDao.addTask(listModel)
    suspend fun updateTask(listModel: ToDolistModel) = todolistDao.updateTask(listModel)
    suspend fun deleteTask(listModel: ToDolistModel) = todolistDao.deleteTask(listModel)


    // create companion object for repository

    companion object{
        private var instance: ToDolistRepository? = null


        // initialized repository function
        fun init(context: Context){
            if(instance == null)
                instance = ToDolistRepository(context)
        }

        // get the repository function
        fun get(): ToDolistRepository{
            return instance ?: throw Exception("To do list Repository must be Initialized")
        }
    }
}