package com.example.todolistapplication.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistapplication.R
import java.text.SimpleDateFormat
import java.util.*

class AddListFragment : Fragment() {

    // calling the view model
    private val listViewModel: TodolistViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // declare variables for all views with their ids
        val titleText: EditText = view.findViewById(R.id.title_edittext)
        val descriptionText: EditText = view.findViewById(R.id.description_EditText)
        val dateText: EditText = view.findViewById(R.id.date_EditText)
        val timeText: EditText = view.findViewById(R.id.time_EditText)
        val timeButton: ImageButton = view.findViewById(R.id.time_ImageButton)
        val dateButton: ImageButton = view.findViewById(R.id.date_ImageButton)
        val saveButton: Button = view.findViewById(R.id.save_button)
        val cancelButton: Button = view.findViewById(R.id.cancelAdd_button)

        // spinner for display the type of tasks
        val spinnerType: Spinner = view.findViewById(R.id.spinner_list)

        // adapter for spinner
        val adabter =  activity?.let { ArrayAdapter.createFromResource(it,
            R.array.type_list, android.R.layout.simple_spinner_item) }
        adabter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerType.adapter = adabter

        // button for display due date
        dateButton.setOnClickListener {
            //getting current day,month and year.
            val calendar: Calendar = Calendar.getInstance()
            val year: Int = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)


         // create date picker dialog and put the test into date EditText
         val dpd = DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                dateText.setText("" + day + "/" + month + "/" + year)
            }, year, month, day)
            dpd.show() }


        // button for display time
        timeButton.setOnClickListener {
            val calendar = Calendar.getInstance()
           val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
               calendar.set(Calendar.HOUR_OF_DAY, hour)
               calendar.set(Calendar.MINUTE, minute)
               timeText.setText( SimpleDateFormat("HH:mm").format(calendar.time))
           }
            TimePickerDialog(view.context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE), false).show()
        }

        // button to save data in database
        saveButton.setOnClickListener {
            val title = titleText.text.toString()
            val descrition = descriptionText.text.toString()
            val date = dateText.text.toString()
            val time = timeText.text.toString()
            val type = spinnerType.selectedItem.toString()

            // calling addTask function from database by viewModel
            listViewModel.addTask(title, type, descrition, date, time)
            findNavController().popBackStack()
        }

        // button to get out from this fragment
        cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}