package com.example.myfridge
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.time.Duration.Companion.days


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

    //Notification related initialisations
    private  var dayNotfication: Int = 0
    private  var monthNotification: Int = 0
    private  var yearNotification: Int = 0

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

                    dayNotfication=dayOfMonth
                    monthNotification=monthOfYear
                    yearNotification=year

                    selectedDateTV.text=(dayOfMonth.toString() +"/"+(monthOfYear+1)+"/"+year)
                    expiryDate = "$dayOfMonth-${monthOfYear+1}-$year"
                },
                year,
                month,
                day

            )
            datePickerDialog.show()
        }

        //Notification related
        createNotificationChannel()

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

                //Function for notifications
                scheduleNotification()

            }else{
                Toast.makeText(context,"Please Fill all the required items",Toast.LENGTH_SHORT).show()

            }
        }
    }

        private fun scheduleNotification() {
        val intent = Intent(applicationContext, Notification::class.java)
        val title="ITEM EXPIRY CLOSE"
        val message="Some items in your fridge are close to expiring"
        intent.putExtra(titleExtra,title)
        intent.putExtra(messageExtra,message)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager= getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time=getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
        Toast.makeText(applicationContext,"Reminder Notification Set!",Toast.LENGTH_SHORT).show()
    }



    //TODO: if time doesn't work properly
    private fun getTime(): Long {
        val hour=10
        val minute=0
        val calendar=Calendar.getInstance()
        calendar.set(yearNotification, monthNotification,dayNotfication,hour,minute)
        return calendar.timeInMillis
    }

    @RequiresApi(Build.VERSION_CODES.O) //Remove if doesn't work
    private fun createNotificationChannel() {
        val name="Notif Channel"
        val desc="A description of the Channel"
        val importance= NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID,name,importance)
        channel.description=desc
        val notificationManager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}

