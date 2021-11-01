package com.example.todolistapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolistapplication.database.model.ToDolistModel

@Dao
interface ToDolistDao {
    @Insert
    suspend fun addTask(listModel: ToDolistModel)

    @Query("SELECT * FROM ToDolistModel")
    fun getTask(): LiveData<List<ToDolistModel>>

    @Update
    suspend fun updateTask(listModel: ToDolistModel)

    @Delete
    suspend fun deleteTask(listModel: ToDolistModel)

}