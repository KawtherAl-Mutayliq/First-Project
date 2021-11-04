package com.example.todolistapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapplication.R
import com.example.todolistapplication.adapter.TodolistAdapter
import com.example.todolistapplication.database.model.ToDolistModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {

    // create variables for view model and data model
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


        // create variables for views
        val addButton: FloatingActionButton= view.findViewById(R.id.add_floatingbutton)
        val sortingButton: ImageButton = view.findViewById(R.id.sorting_button)

        // variables for recyclerview and its adapter
        val listTaskRecyclerView: RecyclerView = view.findViewById(R.id.list_recyclerview)
        val listAdapter = TodolistAdapter(listTask, listViewModel)
        listTaskRecyclerView.adapter = listAdapter


        // object of swipeItem class
        val swipe = object : SwipeItem(view.context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                // delete itemview when swipe to the left
                when(direction){
                    ItemTouchHelper.LEFT -> {

                        listAdapter.delete(viewHolder.adapterPosition)
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipe)
        touchHelper.attachToRecyclerView(listTaskRecyclerView)


        // live data
        listViewModel.todolistTask.observe(viewLifecycleOwner, {
            it?.let {tasks ->
                listTask.clear()
                listTask.addAll(tasks)
                listAdapter.notifyDataSetChanged()
            }
        })

        // add button
        addButton.setOnClickListener {

            findNavController().navigate(R.id.action_mainFragment_to_addListFragment)
        }

        // sorting button
        sortingButton.setOnClickListener {
            listTask.sortByDescending { it.datte }
            listAdapter.notifyDataSetChanged()
        }
    }
}