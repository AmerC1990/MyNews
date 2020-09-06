package com.amercosovic.mynews.receiver

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import com.amercosovic.mynews.NotifiedSearchedNews
import com.amercosovic.mynews.R
import com.amercosovic.mynews.retrofit.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class SearchNotificationsReceiver : BroadcastReceiver() {

    private lateinit var alarmManager: AlarmManager

    override fun onReceive(context: Context, intent: Intent?) {

        val dateFormat = SimpleDateFormat("yyyyMMdd")
        val apiCallDateFormat = dateFormat.format(Date())

        val sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val query = sharedPreferences?.getString("SEARCHNOTIFICATION", null)

        if (query != null) {
            CoroutineScope(IO).launch {
                val result = ApiClient.getClient.getSearchResultForNotification(
                    query = query.toString(),
                    begin_date = apiCallDateFormat.toString(),
                    end_date = apiCallDateFormat.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )
                if (!result.response.docs.isNullOrEmpty()) {
                    withContext(Main) {
                        val editor = sharedPreferences.edit()
                        editor.apply {
                            putString("QUERYSEARCH", query)
                            putString("BEGINDATEANDENDDATESEARCH", apiCallDateFormat.toString())
                        }.apply()
                        val contentIntent = PendingIntent.getActivity(
                            context, 0,
                            Intent(context, NotifiedSearchedNews::class.java), 0
                        )
                        val builder = NotificationCompat.Builder(context, "NotifySearch")
                            .setSmallIcon(R.drawable.myapplogo)
                            .setContentTitle("New articles today from your previous search!")
                            .setContentText("New articles have been published today from your previous search.")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setColor(Color.parseColor("#17B6C8"))

                        builder.setContentIntent(contentIntent)
                        builder.setDefaults(Notification.DEFAULT_SOUND)
                        builder.setAutoCancel(true)
                        val notificationManager =
                            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.notify(300, builder.build())
                    }
                }
            }
        }

// reset Alarm
        setAlarm(context)
    }
    private fun setAlarm(context: Context) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, SearchNotificationsReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val pendingIntent2 =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val myAlarm = AlarmManager.AlarmClockInfo(
            System.currentTimeMillis() + 86400000,
            pendingIntent2
        )
        alarmManager.setAlarmClock(myAlarm, pendingIntent)
    }
}
