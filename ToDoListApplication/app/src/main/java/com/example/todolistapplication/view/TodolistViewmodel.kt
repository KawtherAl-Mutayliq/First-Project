package com.example.todolistapplication.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapplication.database.model.ToDolistModel
import com.example.todolistapplication.repository.ToDolistRepository
import kotlinx.coroutines.launch

class TodolistViewmodel: ViewModel() {

    private val toDolistRepository = ToDolistRepository.get()

    var todolistTask= toDolistRepository.getTask()

    var selectItemMutableLiveData = MutableLiveData<ToDolistModel>()

    fun  addTask(title: String,type:String, description: String, date:String,time:String, dateCreation:String){
        viewModelScope.launch {
            toDolistRepository.addTask(ToDolistModel( title,type, description, date,time, dateCreation))
        }
    }

    fun updateTask(listModel: ToDolistModel){
        viewModelScope.launch {
            toDolistRepository.updateTask(listModel)
        }
    }

    fun deleteTask(listModel: ToDolistModel){
        viewModelScope.launch {
            toDolistRepository.deleteTask(listModel)
        }
    }
}