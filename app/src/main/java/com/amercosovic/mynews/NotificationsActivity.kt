package com.amercosovic.mynews

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amercosovic.mynews.receiver.NotificationsReceiver
import kotlinx.android.synthetic.main.activity_notifications.*

class NotificationsActivity : AppCompatActivity() {

    //declaring variables
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent


    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "amer"
    private val description = "Test notification"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        createNotificationChannel()

        actionBar?.setDisplayHomeAsUpEnabled(true)

        // Get Shared Prefs, if available
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val query = sharedPreferences?.getString("QUERYFORNOTIFICATION", null)

        // If shared Prefs for Notification Are populated, fill out form accordingly when user is
        // back to the notification activity
        if (query != null ) {
            enableNotificationsSwitch.isChecked = true
            search_query_edittext.setText(query.toString().substringBefore("&"))
            when {
                query.contains("Travel") -> {
                    TravelTextBox.isChecked = true
                }
            }
            when {
                query.contains("Business" ) -> {
                    BusinessTextBox.isChecked = true
                }
            }
            when {
                query.contains("Arts" ) -> {
                    ArtsCheckBox.isChecked = true
                }
            }
           when {
                query.contains("Sports" ) -> {
                   SportsTextBox.isChecked = true
               }
           }
            when {
                query.contains("Politics" ) -> {
                    PoliticsTextBox.isChecked = true
                }
            }
            when {
                query.contains("Entrepreneurs" ) -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
        }
        enableNotificationsSwitch.setOnClickListener {
                if (enableNotificationsSwitch.isChecked && search_query_edittext.text.isNotEmpty()
                    && atLeastOneCheckBoxChecked()) {
                    val query = search_query_edittext.text.toString()
                    val categories = getCheckedCategories().toString().replace("[","").replace("]","").replace(",","").replace(" ","")
                        .replace("Politics", "\u0026fq=Politics").replace("Business", "\u0026fq=Business").replace("Entrepreneurs","\u0026fq=Entrepreneurs")
                        .replace("Arts","\u0026fq=Arts").replace("Travel","\u0026fq=Travel").replace("Sports","\u0026fq=Sports")
                    val queryForNotification = query + categories
                            saveQueryAndSetAlarm(queryForNotification)
                    }
                else if (!enableNotificationsSwitch.isChecked) {
                search_query_edittext.setText("")
                when {TravelTextBox.isChecked -> {
                        TravelTextBox.toggle()
                    }
                }
                when {BusinessTextBox.isChecked -> {
                        BusinessTextBox.toggle()
                    }
                }
                when {ArtsCheckBox.isChecked -> {
                        ArtsCheckBox.toggle()
                    }
                }
                when {EntrepreneursTextBox.isChecked -> {
                        EntrepreneursTextBox.toggle()
                    }
                }
                when {PoliticsTextBox.isChecked -> {
                        PoliticsTextBox.toggle()
                    }
                }
                when {SportsTextBox.isChecked -> {
                        SportsTextBox.toggle()
                    }
                }
                val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.remove("QUERYFORNOTIFICATION")
                editor.remove("QUERY")
                editor.remove("BEGINDATEANDENDDATE")
                .apply()
                cancelAlarm()
            }
        }
    }

    // create my notification channel for notification in broadcast receiver
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


    // create set alarm fun and set Alarm Clock to every 24 hours
    private fun setAlarm() {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, NotificationsReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val pendingIntent2 =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val myAlarm = AlarmManager.AlarmClockInfo(
            System.currentTimeMillis() + 86400000,
            pendingIntent2
        )
        alarmManager.setAlarmClock(myAlarm, pendingIntent)
    }
    private fun cancelAlarm() {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, NotificationsReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.cancel(pendingIntent)
    }
    // Check if at least one checkbox is checked
    private fun getCheckedCategories(): List<String> = listOfNotNull(
        "Travel".takeIf { TravelTextBox.isChecked },
        "Sports".takeIf { SportsTextBox.isChecked },
        "Politics".takeIf { PoliticsTextBox.isChecked },
        "Entrepreneurs".takeIf { EntrepreneursTextBox.isChecked },
        "Business".takeIf { BusinessTextBox.isChecked },
        "Arts".takeIf { ArtsCheckBox.isChecked }
    )
    private fun atLeastOneCheckBoxChecked() = getCheckedCategories().isNotEmpty()

    private fun saveQueryAndSetAlarm(queryForNotification: String) {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
        putString("QUERYFORNOTIFICATION",queryForNotification)
        }
        .apply()
        setAlarm()
    }
}
