package com.example.todolistapplication.view

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

    private val listViewModel: TodolistViewmodel by activityViewModels()
    private lateinit var todolistAdapter: TodolistAdapter
    private lateinit var listTask: ToDolistModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleEditText: EditText = view.findViewById(R.id.titleEdit_EditText)
        val descriptionEditText: EditText = view.findViewById(R.id.descriptionEdit_EditText)
        val dateText:EditText = view.findViewById(R.id.dateEdit_EditText)
        val timeText: EditText = view.findViewById(R.id.timeEdit_EditText)
        val timeButton: ImageButton = view.findViewById(R.id.timeEdit_ImageButton)
        val dateButton: ImageButton = view.findViewById(R.id.dateEdit_ImageButton)
        val editButton: Button = view.findViewById(R.id.edit_button)
        val cancelButton: Button = view.findViewById(R.id.cancelEdit_button)



        val spinnerType: Spinner = view.findViewById(R.id.spinnerEdit_list)
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


        var title = titleEditText.toString()
        var description = descriptionEditText.text.toString()
        var datText = dateText.text.toString()
        var timeEditText = timeText.text.toString()
        var selectItem = spinnerType.selectedItem.toString()



       listViewModel.selectItemMutableLiveData.observe(viewLifecycleOwner, {
           it?.let {task ->
               title= task.title
               description =task.description
              datText = task.datte
               timeEditText = task.time
               selectItem = task.type
               todolistAdapter.notifyDataSetChanged()
              listTask = task }
       })



        editButton.setOnClickListener {
            listViewModel.updateTask(listTask)
            findNavController().popBackStack()

        }

        cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}