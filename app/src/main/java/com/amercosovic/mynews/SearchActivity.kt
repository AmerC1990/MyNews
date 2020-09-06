package com.amercosovic.mynews

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amercosovic.mynews.receiver.SearchNotificationsReceiver
import com.amercosovic.mynews.util.buildQueryForNotification
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*
import com.amercosovic.mynews.util.buildQueryForSearchResult

class SearchActivity : AppCompatActivity() {

    private lateinit var alarmManager: AlarmManager

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "NotifySearch"
    private val description = "NotifySearch"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        createNotificationChannel()

        search_query_edittext.getBackground().clearColorFilter();
        actionBar?.setDisplayHomeAsUpEnabled(true)
        Enter_Begin_Date.setPaintFlags(Enter_Begin_Date.getPaintFlags())
        Enter_End_Date.setPaintFlags(Enter_End_Date.getPaintFlags())

        // Calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //  Show Date Picker Dialog once Begin Date or End Date are clicked
        Enter_Begin_Date.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, Year, Month, Day ->
                    val myDay = Day
                    val myMonth = Month + 1
                    val myYear = Year

                    if (myDay < 10 && myMonth < 10) {
                        Enter_Begin_Date.text = "0" + myDay + "/0" + myMonth + "/" + myYear
                    } else if (myDay < 10 && myMonth >= 10) {
                        Enter_Begin_Date.text = "0" + myDay + "/" + myMonth + "/" + myYear
                    } else if (myDay >= 10 && Month < 9) {
                        Enter_Begin_Date.text = "" + myDay + "/0" + myMonth + "/" + myYear
                    } else if (myDay >= 10 && myMonth >= 10) {
                        Enter_Begin_Date.text = "" + myDay + "/" + myMonth + "/" + myYear
                    }
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        Enter_End_Date.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, Year, Month, Day ->
                    val myDay = Day
                    val myMonth = Month + 1
                    val myYear = Year

                    if (myDay < 10 && myMonth < 10) {
                        Enter_End_Date.text = "0" + myDay + "/0" + myMonth + "/" + myYear
                    } else if (myDay < 10 && myMonth >= 10) {
                        Enter_End_Date.text = "0" + myDay + "/" + myMonth + "/" + myYear
                    } else if (myDay >= 10 && Month < 9) {
                        Enter_End_Date.text = "" + myDay + "/0" + myMonth + "/" + myYear
                    } else if (myDay >= 10 && myMonth >= 10) {
                        Enter_End_Date.text = "" + myDay + "/" + myMonth + "/" + myYear
                    }

                },
                year,
                month,
                day
            )
            dpd.show()
        }

//       if search button is clicked and form is filled out according to project brief,
        // save data to shared prefs and send data to results activity / open results activity
        searchButton.setOnClickListener {
            if (search_query_edittext.text.isNotEmpty() && atLeastOneCheckBoxChecked()) {
                saveQueryCancelAndResetAlarm(buildQueryForNotification(searchQuery = search_query_edittext.text.toString(),categories = getCheckedCategories()))
                val intent = Intent(this@SearchActivity, ResultsActivity::class.java)
                intent.putExtra("query",  buildQueryForSearchResult(searchQuery = search_query_edittext.text.toString(),enterBeginDate = Enter_Begin_Date.text.toString(),enterEndDate = Enter_End_Date.text.toString(),categories = getCheckedCategories()))
                Log.d("checkdates", " enter begin date: $Enter_Begin_Date, enter end date: $Enter_End_Date, altered begin date: $Enter_Begin_Date")
                startActivity(intent)
                    }
        }
    }

     private fun getCheckedCategories(): List<String> = listOfNotNull(
        "Travel".takeIf { TravelTextBox.isChecked },
        "Sports".takeIf { SportsTextBox.isChecked },
        "Politics".takeIf { PoliticsTextBox.isChecked },
        "Entrepreneurs".takeIf { EntrepreneursTextBox.isChecked },
        "Business".takeIf { BusinessTextBox.isChecked },
        "Arts".takeIf { ArtsCheckBox.isChecked }
    )

    private fun atLeastOneCheckBoxChecked() = getCheckedCategories().isNotEmpty()

    // create set alarm fun and set Alarm Clock to every 24 hours
    private fun setAlarm() {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, SearchNotificationsReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val pendingIntent2 =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val myAlarm = AlarmManager.AlarmClockInfo(
            System.currentTimeMillis() + 86400000,
            pendingIntent2
        )
        alarmManager.setAlarmClock(myAlarm, pendingIntent)
    }

    private fun cancelAlarm() {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, SearchNotificationsReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        alarmManager.cancel(pendingIntent)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                channelId, description, NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.BLACK
            notificationChannel.enableVibration(false)

            notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun saveQueryCancelAndResetAlarm(queryForSearchNotification: String) {
        cancelAlarm()
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            remove("SEARCHNOTIFICATION")
            remove("QUERYSEARCH")
            remove("BEGINDATEANDENDDATESEARCH")
            putString("SEARCHNOTIFICATION",queryForSearchNotification)
        }
        .apply()
        setAlarm()
    }
}


