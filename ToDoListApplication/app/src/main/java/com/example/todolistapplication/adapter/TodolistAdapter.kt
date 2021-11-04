package com.example.todolistapplication.adapter

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapplication.R
import com.example.todolistapplication.database.model.ToDolistModel
import com.example.todolistapplication.view.TodolistViewmodel
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*

class TodolistAdapter(val taskList: MutableList<ToDolistModel>, val viewModel: TodolistViewmodel) :
    RecyclerView.Adapter<TodolistHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodolistHolder {

        val item_layout = LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout,
            parent, false
        )
        return TodolistHolder(item_layout)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TodolistHolder, position: Int) {

        val task = taskList[position]
        holder.title.text = task.title
        holder.type.text = task.type
        holder.date.text = task.datte
        holder.time.text = task.time
        holder.doneChecked.isChecked = task.checkBox


        holder.itemView.setOnClickListener { view ->
            viewModel.selectItemMutableLiveData.postValue(task)
            view.findNavController().navigate(R.id.action_mainFragment_to_editFragment)
        }

        holder.doneChecked.setOnClickListener {
            if (holder.doneChecked.isChecked) {
                holder.cardView.setBackgroundColor(Color.LTGRAY)
                task.checkBox = true
                viewModel.updateTask(task)

            } else {
                holder.cardView.setBackgroundColor(0)
                task.checkBox = false
                viewModel.updateTask(task)

            }
        }

        val format = SimpleDateFormat("dd/MM/yyyy")
        var currentDate = Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val deadline = format.parse(task.datte).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        if (deadline.isBefore(currentDate)) {
            holder.date.setTextColor(Color.RED)
        } else {
            holder.date.setTextColor(Color.BLACK)
        }
    }



    override fun getItemCount(): Int {
        return taskList.size
    }

    fun delete(i: Int) {

        viewModel.deleteTask(taskList[i])
        notifyDataSetChanged()
    }
}


class TodolistHolder(view: View) : RecyclerView.ViewHolder(view) {

    val title: TextView = view.findViewById(R.id.title_textView)
    val type: TextView = view.findViewById(R.id.typelist_textView)
    val date: TextView = view.findViewById(R.id.date_textview)
    val time: TextView = view.findViewById(R.id.time_textview)
    val doneChecked: CheckBox = view.findViewById(R.id.done_checkbox)
    val cardView: CardView = view.findViewById(R.id.cardView)

}