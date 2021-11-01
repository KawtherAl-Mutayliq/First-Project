package com.example.todolistapplication.repository

import android.content.Context
import androidx.room.Room
import com.example.todolistapplication.database.ToDolistDao
import com.example.todolistapplication.database.ToDolistDatabase
import com.example.todolistapplication.database.model.ToDolistModel
import java.lang.Exception

private const val DATABASE_NAME = "todolist-database"
class ToDolistRepository(context: Context) {

    private val db: ToDolistDatabase = Room.databaseBuilder(context,
        ToDolistDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()

    private val todolistDao = db.toDolistDao()

    fun getTask() = todolistDao.getTask()

    suspend fun addTask(listModel: ToDolistModel) = todolistDao.addTask(listModel)
    suspend fun updateTask(listModel: ToDolistModel) = todolistDao.updateTask(listModel)
    suspend fun deleteTask(listModel: ToDolistModel) = todolistDao.deleteTask(listModel)


    companion object{
        private var instance: ToDolistRepository? = null

        fun init(context: Context){
            if(instance == null)
                instance = ToDolistRepository(context)
        }

        fun get(): ToDolistRepository{
            return instance ?: throw Exception("To do list Repository must be Initialized")
        }
    }


}