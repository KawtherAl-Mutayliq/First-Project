package com.example.todolistapplication.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
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

        // to get creation date
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())


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
         val dpd = DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener
         { view, year, month, day ->
             // display date in edit text
           dateText.setText("" + day + "/" + (month.toInt() +1).toString()  + "/" + year) }, year, month, day)
            dpd.show()
        }


        // button for display time
        timeButton.setOnClickListener {
            val calendar = Calendar.getInstance()
           val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
               calendar.set(Calendar.HOUR_OF_DAY, hour)
               calendar.set(Calendar.MINUTE, minute)
               // display time in edit text
               timeText.setText( SimpleDateFormat("HH:mm a").format(calendar.time))
           }
            TimePickerDialog(view.context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE), false).show()
        }


        // button to save data in database
        saveButton.setOnClickListener {
            val title = titleText.text.toString()
            val description = descriptionText.text.toString()
            val date = dateText.text.toString()
            val time = timeText.text.toString()
            val type = spinnerType.selectedItem.toString()

            if (title.isNotEmpty() && date.isNotEmpty()) {

                // calling addTask function from database by viewModel
                listViewModel.addTask(title, type, description, date, time, currentDate)
                findNavController().popBackStack()
            }else
                Toast.makeText(context, "these Fields must not Empty", Toast.LENGTH_SHORT).show()
        }


        // button to get out from this fragment
        cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}