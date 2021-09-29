//By James Hill September 2021, Philadelphia, PA.

package com.jameshill.ageinminutes

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import java.util.*
import kotlinx.android.synthetic.main.activity_main.* //necessary for communication between this code and activity_main.xml
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener { view ->
            //Toast.makeText(this, "Button Pushed", Toast.LENGTH_LONG).show()
            Log.d(TAG, "calling clickDatePicker")
            clickDatePicker(view)

        }
    }

    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog (this,
        DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            //Set format and display selected date in textView
            val selectedDate = "${selectedMonth + 1}/$selectedDayOfMonth/$selectedYear"
            tvSelectedDate.setText(selectedDate)

            val sdf = SimpleDateFormat ("MM/dd/yyyy", Locale.US)
            val theDate = sdf.parse(selectedDate)

            //.time returns a value in milliseconds. Dividing by 60000 gets the time in minutes.
            //from the epoch (01/01/1970) until the selected date.
            val selectedDateInMinutes = theDate!!.time/60000
            //Current time in milliseconds
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            //How many muntes from the Epoch until today?
            val currentDateInMinutes = currentDate!!.time/60000
            //Calculate the number of minutes from the selected date until now.
            val difference = currentDateInMinutes - selectedDateInMinutes
            // Display result in textView
            tvSelectedDateInMinutes.setText(difference.toString())
            //Calculate number of days
            val days = difference / 1440
            //Display result in textView
            tvSelectedDateInDays.setText(days.toString())

        },year,month,day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show() //end block
    }
}
