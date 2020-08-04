package com.amercosovic.mynews

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.amercosovic.mynews.receiver.AlarmReceiver2
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*

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
                    // set to textView
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
            // show dialog
            dpd.show()
        }

        Enter_End_Date.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, Year, Month, Day ->
                    // set to textView
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
            // show dialog
            dpd.show()
        }

//       if search button is clicked and form is filled out according to project brief,
        // save data to shared prefs and send data to results activity / open results activity

        searchButton.setOnClickListener {
            if (search_query_edittext.text.isNotEmpty() && atLeastOneCheckBoxChecked()) {
                val searchQuery: String = search_query_edittext.text.toString()
                val checkedCategories = getCheckedCategories()
                val beginDate: String? = Enter_Begin_Date.text.toString()
                    .substringAfterLast("/") + Enter_Begin_Date.text.toString()?.substringAfter("/")
                    ?.substringBefore("/") +
                        Enter_Begin_Date.text.toString()?.substringBefore("/")
                val endDate: String? = Enter_End_Date.text.toString()
                    .substringAfterLast("/") + Enter_End_Date.text.toString()?.substringAfter("/")
                    ?.substringBefore("/") +
                        Enter_End_Date.text.toString()?.substringBefore("/")
                if (checkedCategories.count() == 1) {
                    val sharedPreferences =
                        getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    cancelAlarm()
                    editor.apply {
                        remove("QUERYSEARCH")
                        remove("CATEGORY1SEARCH")
                        remove("CATEGORY2SEARCH")
                        remove("CATEGORY3SEARCH")
                        remove("CATEGORY4SEARCH")
                        remove("CATEGORY5SEARCH")
                        remove("CATEGORY6SEARCH")
                        remove("BEGINDATEANDENDDATESEARCH")
                        putString("QUERYSEARCH", searchQuery)
                        putString("CATEGORY1SEARCH", checkedCategories.component1())
                    }.apply()
                    setAlarm()
                    val intent = Intent(this@SearchActivity, ResultsActivity::class.java)
                    intent.putExtra("query", searchQuery)
                    intent.putStringArrayListExtra(
                        "checkedCategories",
                        ArrayList(checkedCategories)
                    )
                    intent.putExtra("beginDate", beginDate)
                    intent.putExtra("endDate", endDate)
                    startActivity(intent)
                } else if (checkedCategories.count() == 2) {
                    val sharedPreferences =
                        getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    cancelAlarm()
                    editor.apply {
                        remove("QUERYSEARCH")
                        remove("CATEGORY1SEARCH")
                        remove("CATEGORY2SEARCH")
                        remove("CATEGORY3SEARCH")
                        remove("CATEGORY4SEARCH")
                        remove("CATEGORY5SEARCH")
                        remove("CATEGORY6SEARCH")
                        remove("BEGINDATEANDENDDATESEARCH")
                        putString("QUERYSEARCH", searchQuery)
                        putString("CATEGORY1SEARCH", checkedCategories.component1())
                        putString("CATEGORY2SEARCH", checkedCategories.component2())
                    }.apply()
                    setAlarm()
                    val intent = Intent(this@SearchActivity, ResultsActivity::class.java)
                    intent.putExtra("query", searchQuery)
                    intent.putStringArrayListExtra(
                        "checkedCategories",
                        ArrayList(checkedCategories)
                    )
                    intent.putExtra("beginDate", beginDate)
                    intent.putExtra("endDate", endDate)
                    startActivity(intent)
                } else if (checkedCategories.count() == 3) {
                    val sharedPreferences =
                        getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    cancelAlarm()
                    editor.apply {
                        remove("QUERYSEARCH")
                        remove("CATEGORY1SEARCH")
                        remove("CATEGORY2SEARCH")
                        remove("CATEGORY3SEARCH")
                        remove("CATEGORY4SEARCH")
                        remove("CATEGORY5SEARCH")
                        remove("CATEGORY6SEARCH")
                        remove("BEGINDATEANDENDDATESEARCH")
                        putString("QUERYSEARCH", searchQuery)
                        putString("CATEGORY1SEARCH", checkedCategories.component1())
                        putString("CATEGORY2SEARCH", checkedCategories.component2())
                        putString("CATEGORY3SEARCH", checkedCategories.component3())
                    }.apply()
                    setAlarm()
                    val intent = Intent(this@SearchActivity, ResultsActivity::class.java)
                    intent.putExtra("query", searchQuery)
                    intent.putStringArrayListExtra(
                        "checkedCategories",
                        ArrayList(checkedCategories)
                    )
                    intent.putExtra("beginDate", beginDate)
                    intent.putExtra("endDate", endDate)
                    startActivity(intent)
                } else if (checkedCategories.count() == 4) {
                    val sharedPreferences =
                        getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    cancelAlarm()
                    editor.apply {
                        remove("QUERYSEARCH")
                        remove("CATEGORY1SEARCH")
                        remove("CATEGORY2SEARCH")
                        remove("CATEGORY3SEARCH")
                        remove("CATEGORY4SEARCH")
                        remove("CATEGORY5SEARCH")
                        remove("CATEGORY6SEARCH")
                        remove("BEGINDATEANDENDDATESEARCH")
                        putString("QUERYSEARCH", searchQuery)
                        putString("CATEGORY1SEARCH", checkedCategories.component1())
                        putString("CATEGORY2SEARCH", checkedCategories.component2())
                        putString("CATEGORY3SEARCH", checkedCategories.component3())
                        putString("CATEGORY4SEARCH", checkedCategories.component4())
                    }.apply()
                    setAlarm()
                    val intent = Intent(this@SearchActivity, ResultsActivity::class.java)
                    intent.putExtra("query", searchQuery)
                    intent.putStringArrayListExtra(
                        "checkedCategories",
                        ArrayList(checkedCategories)
                    )
                    intent.putExtra("beginDate", beginDate)
                    intent.putExtra("endDate", endDate)
                    startActivity(intent)
                } else if (checkedCategories.count() == 5) {
                    val sharedPreferences =
                        getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    cancelAlarm()
                    editor.apply {
                        remove("QUERYSEARCH")
                        remove("CATEGORY1SEARCH")
                        remove("CATEGORY2SEARCH")
                        remove("CATEGORY3SEARCH")
                        remove("CATEGORY4SEARCH")
                        remove("CATEGORY5SEARCH")
                        remove("CATEGORY6SEARCH")
                        remove("BEGINDATEANDENDDATESEARCH")
                        putString("QUERYSEARCH", searchQuery)
                        putString("CATEGORY1SEARCH", checkedCategories.component1())
                        putString("CATEGORY2SEARCH", checkedCategories.component2())
                        putString("CATEGORY3SEARCH", checkedCategories.component3())
                        putString("CATEGORY4SEARCH", checkedCategories.component4())
                        putString("CATEGORY5SEARCH", checkedCategories.component5())
                    }.apply()
                    setAlarm()
                    val intent = Intent(this@SearchActivity, ResultsActivity::class.java)
                    intent.putExtra("query", searchQuery)
                    intent.putStringArrayListExtra(
                        "checkedCategories",
                        ArrayList(checkedCategories)
                    )
                    intent.putExtra("beginDate", beginDate)
                    intent.putExtra("endDate", endDate)
                    startActivity(intent)
                } else if (checkedCategories.count() == 6) {
                    val sharedPreferences =
                        getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    cancelAlarm()
                    editor.apply {
                        remove("QUERYSEARCH")
                        remove("CATEGORY1SEARCH")
                        remove("CATEGORY2SEARCH")
                        remove("CATEGORY3SEARCH")
                        remove("CATEGORY4SEARCH")
                        remove("CATEGORY5SEARCH")
                        remove("CATEGORY6SEARCH")
                        remove("BEGINDATEANDENDDATESEARCH")
                        putString("QUERYSEARCH", searchQuery)
                        putString("CATEGORY1SEARCH", checkedCategories.component1())
                        putString("CATEGORY2SEARCH", checkedCategories.component2())
                        putString("CATEGORY3SEARCH", checkedCategories.component3())
                        putString("CATEGORY4SEARCH", checkedCategories.component4())
                        putString("CATEGORY5SEARCH", checkedCategories.component5())
                        putString("CATEGORY6SEARCH", checkedCategories.last())
                    }.apply()
                    setAlarm()
                    val intent = Intent(this@SearchActivity, ResultsActivity::class.java)
                    intent.putExtra("query", searchQuery)
                    intent.putStringArrayListExtra(
                        "checkedCategories",
                        ArrayList(checkedCategories)
                    )
                    intent.putExtra("beginDate", beginDate)
                    intent.putExtra("endDate", endDate)
                    startActivity(intent)
                }

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

    private fun setAlarm() {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver2::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        // Setting the specific time for the alarm manager to trigger the intent, here, the alarm is going off at 11:59 everyday
//        val calendar = Calendar.getInstance()
//        calendar.timeInMillis = System.currentTimeMillis()
//        calendar.set(Calendar.HOUR,6)
//        calendar.set(Calendar.MINUTE,2)
//        calendar.set(Calendar.SECOND,30)

        var firstTime = SystemClock.elapsedRealtime()
        firstTime += (30 * 1000).toLong()

//         Starts the alarm manager
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + 86400000,
            pendingIntent
        )
    }

    private fun cancelAlarm() {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver2::class.java)
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
}


