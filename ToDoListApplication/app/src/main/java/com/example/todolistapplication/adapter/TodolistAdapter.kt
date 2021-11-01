package com.example.todolistapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapplication.R
import com.example.todolistapplication.database.model.ToDolistModel
import com.example.todolistapplication.view.TodolistViewmodel

class TodolistAdapter(val taskList: MutableList<ToDolistModel>, val viewModel: TodolistViewmodel)
    : RecyclerView.Adapter<TodolistHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodolistHolder {

       val item_layout = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,
       parent, false)
        return TodolistHolder(item_layout)
    }

    override fun onBindViewHolder(holder: TodolistHolder, position: Int) {
         val task = taskList[position]
        holder.title.text = task.title
        holder.type.text = task.type
        holder.date.text = task.datte
        holder.time.text = task.time

    }

    override fun getItemCount(): Int {
      return taskList.size
    }
}


class TodolistHolder(view: View): RecyclerView.ViewHolder(view){

    val title: TextView = view.findViewById(R.id.title_textView)
    val type: TextView = view.findViewById(R.id.typelist_textView)
   val date : TextView = view.findViewById(R.id.date_textview)
    val time : TextView = view.findViewById(R.id.time_textview)
    val doneChecked: CheckBox = view.findViewById(R.id.done_checkbox)

}