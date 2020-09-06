package com.amercosovic.mynews.receiver

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import com.amercosovic.mynews.NotifiedNews
import com.amercosovic.mynews.R
import com.amercosovic.mynews.SearchActivity
import com.amercosovic.mynews.retrofit.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class NotificationsReceiver : BroadcastReceiver() {

    private lateinit var alarmManager: AlarmManager

    override fun onReceive(context: Context, intent: Intent?) {

        val dateFormat = SimpleDateFormat("yyyyMMdd")
        val apiCallDateFormat = dateFormat.format(Date())

        // get shared prefs
        val sharedPreferences = context?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val query = sharedPreferences?.getString("QUERYFORNOTIFICATION", null)


        // if shared prefs are not empty, for each combination , make api call every 24 hours
        // and if api call is not null or empty, fire notification
        if (query != null ) {
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
                            putString("QUERY", query)
                            putString("BEGINDATEANDENDDATE", apiCallDateFormat.toString())
                        }.apply()
                        val contentIntent = PendingIntent.getActivity(
                            context, 0,
                            Intent(context, NotifiedNews::class.java), 0
                        )
                        val builder = NotificationCompat.Builder(context, "amer")
                            .setSmallIcon(R.drawable.myapplogo)
                            .setContentTitle("New articles today!")
                            .setContentText("New articles have been published today.")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setColor(Color.parseColor("#17B6C8"))


                        builder.setContentIntent(contentIntent)
                        builder.setDefaults(Notification.DEFAULT_SOUND)
                        builder.setAutoCancel(true)
                        val notificationManager =
                            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.notify(200, builder.build())
                    }
                }
            }
        }
// reset alarm
        setAlarm(context)
    }
    private fun setAlarm(context: Context) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationsReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val pendingIntent2 =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val myAlarm = AlarmManager.AlarmClockInfo(
            System.currentTimeMillis() + 86400000,
            pendingIntent2
        )
        alarmManager.setAlarmClock(myAlarm, pendingIntent)
    }
}




