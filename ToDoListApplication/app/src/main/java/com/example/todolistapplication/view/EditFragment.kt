package com.example.todolistapplication.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistapplication.R
import com.example.todolistapplication.adapter.TodolistAdapter
import com.example.todolistapplication.database.model.ToDolistModel
import java.text.SimpleDateFormat
import java.util.*


class EditFragment : Fragment() {

    // create variables for view model and data model
    private val listViewModel: TodolistViewmodel by activityViewModels()
    private lateinit var listTask: ToDolistModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // declare variables for all views with their ids

        val titleEditText: EditText = view.findViewById(R.id.titleEdit_EditText)
        val descriptionEditText: EditText = view.findViewById(R.id.descriptionEdit_EditText)
        val dateText:EditText = view.findViewById(R.id.dateEdit_EditText)
        val timeText: EditText = view.findViewById(R.id.timeEdit_EditText)
        val timeButton: ImageButton = view.findViewById(R.id.timeEdit_ImageButton)
        val dateButton: ImageButton = view.findViewById(R.id.dateEdit_ImageButton)
        val editButton: Button = view.findViewById(R.id.edit_button)
        val cancelButton: Button = view.findViewById(R.id.cancelEdit_button)
        val creationDate: TextView = view.findViewById(R.id.datecreation_text)

        // format date for display creation date
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm a")
        val currentDate = sdf.format(Date())

        // spinner for type of task
        val spinnerType: Spinner = view.findViewById(R.id.spinnerEdit_list)
        val adabter =  activity?.let { ArrayAdapter.createFromResource(it,
           R.array.type_list, android.R.layout.simple_spinner_item) }
        adabter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerType.adapter = adabter

        var selectedItem = spinnerType.selectedItem



        // button for display due date
        dateButton.setOnClickListener {
            //getting current day,month and year.
            val calendar: Calendar = Calendar.getInstance()
            val year: Int = calendar.get(Calendar.YEAR)
            var month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)



            // create date picker dialog and put the test into date EditText
            val dpd = DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener
            { view, year, month, day ->
             // display date of due date in text
             dateText.setText("" + day + "/" + (month.toInt() +1).toString()  + "/" + year) }, year, month, day)
            dpd.show()
        }


        // button for time
        timeButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
               // display time text
                timeText.setText( SimpleDateFormat("HH:mm a").format(calendar.time))
            }
            // time picker
            TimePickerDialog(view.context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), false).show()
        }


        // live data
       listViewModel.selectItemMutableLiveData.observe(viewLifecycleOwner, {
           it?.let {task ->
              titleEditText.setText(task.title)
               descriptionEditText.setText(task.description)
               selectedItem= task.type
               dateText.setText(task.datte)
               timeText.setText(task.time)
               creationDate.text = "created at ${task.dateCreation}"
               listTask = task
           }
       })

        // edit button for edit fragment
        editButton.setOnClickListener {

            val titletext = titleEditText.text.toString()
            val descText = descriptionEditText.text.toString()
            val listType = spinnerType.selectedItem.toString()
            val date = dateText.text.toString()
            val time = timeText.text.toString()
            var dayDate = currentDate

            listTask.title = titletext
            listTask.description = descText
            listTask.type = listType
            listTask.datte = date
            listTask.time = time
            listTask.dateCreation = "created at $dayDate"

            listViewModel.updateTask(listTask)

            findNavController().popBackStack()

        }

        // cancel button for edit fragment
        cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}