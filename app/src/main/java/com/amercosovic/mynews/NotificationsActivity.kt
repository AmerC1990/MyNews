package com.amercosovic.mynews

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.amercosovic.mynews.receiver.AlarmReceiver
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
        val query = sharedPreferences?.getString("QUERY", null)
        val category1 = sharedPreferences?.getString("CATEGORY1", null)
        val category2 = sharedPreferences?.getString("CATEGORY2", null)
        val category3 = sharedPreferences?.getString("CATEGORY3", null)
        val category4 = sharedPreferences?.getString("CATEGORY4", null)
        val category5 = sharedPreferences?.getString("CATEGORY5", null)
        val category6 = sharedPreferences?.getString("CATEGORY6", null)


        // If shared Prefs for Notification Are populated, fill out form accordingly when user is
        // back to the notification activity
        if (query != null && category1 != null && category2 == null && category3 == null && category4 == null && category5 == null && category6 == null) {
            enableNotificationsSwitch.isChecked = true
            search_query_edittext.setText(query)
            when {
                category1.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category1.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category1.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category1.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category1.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category1.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
        } else if (query != null && category1 != null && category2 != null && category3 == null && category4 == null && category5 == null && category6 == null) {
            enableNotificationsSwitch.isChecked = true
            search_query_edittext.setText(query)
            when {
                category1.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category1.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category1.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category1.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category1.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category1.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category2.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category2.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category2.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category2.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category2.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category2.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
        } else if (query != null && category1 != null && category2 != null && category3 != null && category4 == null && category5 == null && category6 == null) {
            enableNotificationsSwitch.isChecked = true
            search_query_edittext.setText(query)
            when {
                category1.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category1.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category1.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category1.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category1.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category1.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category2.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category2.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category2.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category2.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category2.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category2.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category3.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category3.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category3.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category3.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category3.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category3.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
        } else if (query != null && category1 != null && category2 != null && category3 != null && category4 != null && category5 == null && category6 == null) {
            enableNotificationsSwitch.isChecked = true
            search_query_edittext.setText(query)
            when {
                category1.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category1.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category1.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category1.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category1.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category1.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category2.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category2.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category2.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category2.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category2.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category2.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category3.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category3.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category3.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category3.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category3.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category3.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category4.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category4.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category4.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category4.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category4.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category4.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
        } else if (query != null && category1 != null && category2 != null && category3 != null && category4 != null && category5 != null && category6 == null) {
            enableNotificationsSwitch.isChecked = true
            search_query_edittext.setText(query)
            when {
                category1.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category1.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category1.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category1.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category1.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category1.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category2.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category2.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category2.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category2.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category2.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category2.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category3.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category3.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category3.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category3.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category3.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category3.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category4.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category4.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category4.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category4.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category4.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category4.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category5.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category5.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category5.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category5.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category5.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category5.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
        } else if (query != null && category1 != null && category2 != null && category3 != null && category4 != null && category5 != null && category6 != null) {
            enableNotificationsSwitch.isChecked = true
            search_query_edittext.setText(query)
            when {
                category1.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category1.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category1.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category1.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category1.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category1.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category2.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category2.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category2.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category2.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category2.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category2.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category3.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category3.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category3.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category3.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category3.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category3.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category4.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category4.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category4.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category4.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category4.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category4.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category5.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category5.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category5.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category5.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category5.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category5.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
            when {
                category6.toString() == "Travel" -> {
                    TravelTextBox.isChecked = true
                }
                category6.toString() == "Business" -> {
                    BusinessTextBox.isChecked = true
                }
                category6.toString() == "Arts" -> {
                    ArtsCheckBox.isChecked = true
                }
                category6.toString() == "Sports" -> {
                    SportsTextBox.isChecked = true
                }
                category6.toString() == "Politics" -> {
                    PoliticsTextBox.isChecked = true
                }
                category6.toString() == "Entrepreneurs" -> {
                    EntrepreneursTextBox.isChecked = true
                }
            }
        }


        // if notification switch is enabled and form is properly filled out,
        // take data from filled out form and save to shared prefs and set alarm
        enableNotificationsSwitch.setOnClickListener {
            if (enableNotificationsSwitch.isChecked && search_query_edittext.text.isNotEmpty() && getCheckedCategories().count() == 1) {
                val query = search_query_edittext.text.toString()
                val category = getCheckedCategories()
                val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
//                editor.clear()
//                    .apply()
                editor.apply {
                    putString("QUERY", query)
                    putString("CATEGORY1", category.component1())
                }.apply()
                setAlarm()
            } else if (enableNotificationsSwitch.isChecked && search_query_edittext.text.isNotEmpty() && getCheckedCategories().count() == 2) {
                val query = search_query_edittext.text.toString()
                val category = getCheckedCategories()
                val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.apply {
                    putString("QUERY", query)
                    putString("CATEGORY1", category.component1())
                    putString("CATEGORY2", category.component2())
                }.apply()
                setAlarm()
            } else if (enableNotificationsSwitch.isChecked && search_query_edittext.text.isNotEmpty() && getCheckedCategories().count() == 3) {
                val query = search_query_edittext.text.toString()
                val category = getCheckedCategories()
                val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.apply {
                    putString("QUERY", query)
                    putString("CATEGORY1", category.component1())
                    putString("CATEGORY2", category.component2())
                    putString("CATEGORY3", category.component3())
                }.apply()
                setAlarm()
            } else if (enableNotificationsSwitch.isChecked && search_query_edittext.text.isNotEmpty() && getCheckedCategories().count() == 4) {
                val query = search_query_edittext.text.toString()
                val category = getCheckedCategories()
                val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.apply {
                    putString("QUERY", query)
                    putString("CATEGORY1", category.component1())
                    putString("CATEGORY2", category.component2())
                    putString("CATEGORY3", category.component3())
                    putString("CATEGORY4", category.component4())
                }.apply()
                setAlarm()
            } else if (enableNotificationsSwitch.isChecked && search_query_edittext.text.isNotEmpty() && getCheckedCategories().count() == 5) {
                val query = search_query_edittext.text.toString()
                val category = getCheckedCategories()
                val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.apply {
                    putString("QUERY", query)
                    putString("CATEGORY1", category.component1())
                    putString("CATEGORY2", category.component2())
                    putString("CATEGORY3", category.component3())
                    putString("CATEGORY4", category.component4())
                    putString("CATEGORY5", category.component5())
                }.apply()
                setAlarm()
            } else if (enableNotificationsSwitch.isChecked && search_query_edittext.text.isNotEmpty() && getCheckedCategories().count() == 6) {
                val query = search_query_edittext.text.toString()
                val category = getCheckedCategories()
                val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.apply {
                    putString("QUERY", query)
                    putString("CATEGORY1", category.component1())
                    putString("CATEGORY2", category.component2())
                    putString("CATEGORY3", category.component3())
                    putString("CATEGORY4", category.component4())
                    putString("CATEGORY5", category.component5())
                    putString("CATEGORY6", category.last())
                }.apply()
                setAlarm()
            }
            // other wise, when shared prefs are full, notification toggle is on, when
            // user turns notification toggle off - remove data from form, empty notifications
            // data from shared prefs, and cancel alarm
            else if (!enableNotificationsSwitch.isChecked) {
                search_query_edittext.setText("")
                when {
                    TravelTextBox.isChecked -> {
                        TravelTextBox.toggle()
                    }
                }
                when {
                    BusinessTextBox.isChecked -> {
                        BusinessTextBox.toggle()
                    }
                }
                when {
                    ArtsCheckBox.isChecked -> {
                        ArtsCheckBox.toggle()
                    }
                }
                when {
                    EntrepreneursTextBox.isChecked -> {
                        EntrepreneursTextBox.toggle()
                    }
                }
                when {
                    PoliticsTextBox.isChecked -> {
                        PoliticsTextBox.toggle()
                    }
                }
                when {
                    SportsTextBox.isChecked -> {
                        SportsTextBox.toggle()
                    }
                }
//                enableNotificationsSwitch.toggle()
                val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.remove("QUERY")
                editor.remove("CATEGORY1")
                editor.remove("CATEGORY2")
                editor.remove("CATEGORY3")
                editor.remove("CATEGORY4")
                editor.remove("CATEGORY5")
                editor.remove("CATEGORY6")
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
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val pendingIntent2 =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Setting the specific time for the alarm manager to trigger the intent, here, the alarm is going off at 11:59 everyday
//        val calendar = Calendar.getInstance()
//        calendar.timeInMillis = System.currentTimeMillis()
//        calendar.set(Calendar.HOUR,6)
//        calendar.set(Calendar.MINUTE,2)
//        calendar.set(Calendar.SECOND,30)

        var firstTime = SystemClock.elapsedRealtime()
        firstTime += (30 * 1000).toLong()

//         Starts the alarm manager
//        alarmManager.setExactAndAllowWhileIdle(
//            AlarmManager.RTC_WAKEUP,
//            System.currentTimeMillis() + 86400000,
//            pendingIntent
//        )
//        alarmManager.setAlarmClock(
//            AlarmManager.AlarmClockInfo(System.currentTimeMillis() + 86400000, pendingIntent),
//            pendingIntent
//        )

        val ac = AlarmManager.AlarmClockInfo(
            System.currentTimeMillis() + 86400000,
            pendingIntent2
        )
        alarmManager.setAlarmClock(ac, pendingIntent)
    }

    // set to every minute/ 60 seconds
//    System.currentTimeMillis() + 60000,
    //

    // 8320 * 10

    private fun cancelAlarm() {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
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


}
