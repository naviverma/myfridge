package com.example.myfridge
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*


class MainActivity : AppCompatActivity() {

    var name: String = ""
    var email: String = ""
    var favoriteNumber: Int = 0

    lateinit var nameInput: EditText//it is the property of variable which let us to change through frontend
    lateinit var emailInput: EditText
    lateinit var favoriteNumberInput: EditText
    lateinit var pickDateBtn: Button
    lateinit var selectedDateTV: TextView

    lateinit var submitButton: Button//button property

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)//connect your frontend with backend

        pickDateBtn=findViewById(R.id.idBtnPickDate)
        selectedDateTV=findViewById(R.id.idTVSelectedDate)

        pickDateBtn.setOnClickListener {
            val c= Calendar.getInstance()
            val year =c.get(Calendar.YEAR)
            val month=c.get(Calendar.MONTH)
            val day=c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog =DatePickerDialog(
                this,
                {view,year,monthOfYear,dayOfMonth->
                    selectedDateTV.text=(dayOfMonth.toString() +"-"+(monthOfYear+1)+"-"+year)

                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        nameInput = findViewById(R.id.nameInput)
        emailInput = findViewById(R.id.emailInput)
        favoriteNumberInput = findViewById(R.id.favoriteNumberInput)

        submitButton = findViewById(R.id.submitButton)
        submitButton.setOnClickListener {
            name = nameInput.text.toString()
            email = emailInput.text.toString()
            favoriteNumber = favoriteNumberInput.text.toString().toInt()
        }
    }
}

