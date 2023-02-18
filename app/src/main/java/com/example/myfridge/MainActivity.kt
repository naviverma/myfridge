package com.example.myfridge
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    var gName: String = ""
    var website: String = ""
    var noOfItems: Int = 0
    var expiryDate : String = ""

    lateinit var gNameInput: EditText//it is the property of variable which let us to change through frontend
    lateinit var websiteInput: EditText
    lateinit var noOfItemsInput: EditText
    lateinit var pickDateBtn: Button
    lateinit var selectedDateTV: TextView

    lateinit var submitButton: Button//button property

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)//connect your frontend with backend
        val context = this
        val button: Button = findViewById(R.id.listButton)
        button.setOnClickListener {
            val intent= Intent(this,MainActivity2::class.java)
            startActivity(intent)

        }

        pickDateBtn=findViewById(R.id.idBtnPickDate)
        selectedDateTV=findViewById(R.id.idTVSelectedDate)

        pickDateBtn.setOnClickListener {
            val c= Calendar.getInstance()
            var year =c.get(Calendar.YEAR)
            var month=c.get(Calendar.MONTH)
            var day=c.get(Calendar.DAY_OF_MONTH)
            var  datePickerDialog =DatePickerDialog(
                this,
                { view,year,monthOfYear,dayOfMonth->
                    selectedDateTV.text=(dayOfMonth.toString() +"/"+(monthOfYear+1)+"/"+year)
                    expiryDate = "$dayOfMonth-${monthOfYear+1}-$year"//very very sexy
                },
                year,
                month,
                day

            )
            datePickerDialog.show()




        }

        gNameInput = findViewById(R.id.nameInput)
        websiteInput = findViewById(R.id.emailInput)
        noOfItemsInput = findViewById(R.id.favoriteNumberInput)

        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            if(gNameInput.text.toString().isNotEmpty() &&
                websiteInput.text.toString().isNotEmpty() &&
                noOfItemsInput.text.toString().toInt() > 0 &&
                expiryDate.isNotEmpty() ){
                var glist =GList(gNameInput.text.toString(),websiteInput.text.toString(),noOfItemsInput.text.toString().toInt(),expiryDate.toString())
                var db= DataBaseHandler(context)
                db.insertData(glist)


            }else{
                Toast.makeText(context,"Please Fill all the required items",Toast.LENGTH_SHORT).show()

            }
        }
    }
}

