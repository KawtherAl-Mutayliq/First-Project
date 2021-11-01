package com.example.todolistapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapplication.R
import com.example.todolistapplication.adapter.TodolistAdapter
import com.example.todolistapplication.database.model.ToDolistModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {


    private var listTask = mutableListOf<ToDolistModel>()
    private val listViewModel: TodolistViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val listTaskRecyclerView: RecyclerView = view.findViewById(R.id.list_recyclerview)
        val listAdapter = TodolistAdapter(listTask, listViewModel)

        listTaskRecyclerView.adapter = listAdapter
        val addButton: FloatingActionButton= view.findViewById(R.id.add_floatingbutton)

        listViewModel.todolistTask.observe(viewLifecycleOwner, {
            it?.let {tasks ->
                listTask.clear()
                listTask.addAll(tasks)
                listAdapter.notifyDataSetChanged()
            }
        })
        addButton.setOnClickListener {

            findNavController().navigate(R.id.action_mainFragment_to_addListFragment)
        }


    }


}