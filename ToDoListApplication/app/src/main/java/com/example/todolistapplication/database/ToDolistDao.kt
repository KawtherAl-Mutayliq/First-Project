package com.example.todolistapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolistapplication.database.model.ToDolistModel

//  the interface of database
@Dao
interface ToDolistDao {

    // add items into table
    @Insert
    suspend fun addTask(listModel: ToDolistModel)

    // get items from database
    @Query("SELECT * FROM ToDolistModel")
    fun getTask(): LiveData<List<ToDolistModel>>

    // update items of database
    @Update
    suspend fun updateTask(listModel: ToDolistModel)

    // delete items from database
    @Delete
    suspend fun deleteTask(listModel: ToDolistModel)

}