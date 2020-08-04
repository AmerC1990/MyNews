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

class AlarmReceiver2 : BroadcastReceiver() {

    private lateinit var alarmManager: AlarmManager

    override fun onReceive(context: Context, intent: Intent?) {


        Log.d("amer", "Alarm Receiver Working")

        val sdf2 = SimpleDateFormat("yyyy/MM/dd")
        val currentTime = sdf2.format(Date())
        val sdf3 = SimpleDateFormat("yyyyMMdd")
        val apiCallDateFormat = sdf3.format(Date())
//        Log.d("amer", result.response.docs.filter { it.multimedia.isNotEmpty() }.toString().substringAfter("url=images/"))

        val sharedPreferences = context?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val query = sharedPreferences?.getString("QUERYSEARCH", null)
        val category1 = sharedPreferences?.getString("CATEGORY1SEARCH", null)
        val category2 = sharedPreferences?.getString("CATEGORY2SEARCH", null)
        val category3 = sharedPreferences?.getString("CATEGORY3SEARCH", null)
        val category4 = sharedPreferences?.getString("CATEGORY4SEARCH", null)
        val category5 = sharedPreferences?.getString("CATEGORY5SEARCH", null)
        val category6 = sharedPreferences?.getString("CATEGORY6SEARCH", null)

        if (query != null && category1 != null && category2 == null && category3 == null &&
            category4 == null && category5 == null && category6 == null
        ) {
//            Toast.makeText(context, "Saved from Receiver", Toast.LENGTH_LONG).show()
            CoroutineScope(IO).launch {
                val result = ApiClient.getClient.getSearchResultWith1CheckboxBeginDateEndDate(
                    query = query.toString(),
                    category1 = category1.toString(),
                    begin_date = apiCallDateFormat.toString(),
                    end_date = apiCallDateFormat.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )
//                val lastNumberOfCurrentTime = currentTime.subSequence(9,10)
//                    val dateFromApiCall = "${ result.response.docs.filter { it.multimedia.isNotEmpty() }.toString()
//                        .substringAfter("url=images/").subSequence(0, 9).toString()}" + "$lastNumberOfCurrentTime"
                if (!result.response.docs.isNullOrEmpty()) {
                    withContext(Main) {
                        val editor = sharedPreferences.edit()
                        editor.apply {
                            putString("QUERYSEARCH", query)
                            putString("CATEGORY1SEARCH", category1)
                            putString("BEGINDATEANDENDDATESEARCH", apiCallDateFormat.toString())
                        }.apply()
                        val contentIntent = PendingIntent.getActivity(
                            context, 0,
                            Intent(context, NotifiedSearchedNews::class.java), 0
                        )
                        val builder = NotificationCompat.Builder(context, "NotifySearch")
                            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                            .setContentTitle("New articles today from your previous search!")
                            .setContentText("New Articles have been published today in $query - $category1")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setColor(Color.parseColor("#17B6C8"))


                        builder.setContentIntent(contentIntent)
                        builder.setDefaults(Notification.DEFAULT_SOUND)
                        builder.setAutoCancel(true)
                        val notificationManager =
                            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.notify(300, builder.build())
                    }
                    //

//                    Log.d("FromReceiver", "number of last digit in current time : $lastNumberOfCurrentTime")
//                    Log.d(
//                        "FromReceiver","date from api call in images: ${ result.response.docs.filter { it.multimedia.isNotEmpty() }.toString()
//                            .substringAfter("url=images/").subSequence(0, 9).toString()}$lastNumberOfCurrentTime"
//                    )
//                    Log.d("FromReceiver","current time $currentTime" )

                }
            }

        } else if (query != null && category1 != null && category2 != null && category3 == null &&
            category4 == null && category5 == null && category6 == null
        ) {
            CoroutineScope(IO).launch {
                val result = ApiClient.getClient.getSearchResultWith2CheckboxBeginDateEndDate(
                    query = query.toString(),
                    category1 = category1.toString(),
                    category2 = category2.toString(),
                    begin_date = apiCallDateFormat.toString(),
                    end_date = apiCallDateFormat.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )
//                val lastNumberOfCurrentTime = currentTime.subSequence(9,10)
//                val dateFromApiCall = "${ result.response.docs.filter { it.multimedia.isNotEmpty() }.toString()
//                    .substringAfter("url=images/").subSequence(0, 9).toString()}" + "$lastNumberOfCurrentTime"
                if (!result.response.docs.isNullOrEmpty()) {
                    withContext(Main) {
                        val editor = sharedPreferences.edit()
                        editor.apply {
                            putString("QUERYSEARCH", query)
                            putString("CATEGORY1SEARCH", category1)
                            putString("CATEGORY2SEARCH", category2)
                            putString("BEGINDATEANDENDDATESEARCH", apiCallDateFormat.toString())
                        }.apply()
                        val contentIntent = PendingIntent.getActivity(
                            context, 0,
                            Intent(context, NotifiedSearchedNews::class.java), 0
                        )
                        val builder = NotificationCompat.Builder(context, "NotifySearch")
                            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                            .setContentTitle("New articles today from your previous search!")
                            .setContentText("New Articles have been published today in $query - $category1 and $category2")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setColor(Color.parseColor("#17B6C8"))

                        builder.setContentIntent(contentIntent)
                        builder.setDefaults(Notification.DEFAULT_SOUND)
                        builder.setAutoCancel(true)
                        val notificationManager =
                            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.notify(300, builder.build())
                    }
                    //

//                    Log.d("FromReceiver", "number of last digit in current time : $lastNumberOfCurrentTime")
//                    Log.d(
//                        "FromReceiver","date from api call in images: ${ result.response.docs.filter { it.multimedia.isNotEmpty() }.toString()
//                            .substringAfter("url=images/").subSequence(0, 9).toString()}$lastNumberOfCurrentTime"
//                    )
//                    Log.d("FromReceiver","current time $currentTime" )

                }

            }

        } else if (query != null && category1 != null && category2 != null && category3 != null &&
            category4 == null && category5 == null && category6 == null
        ) {
            CoroutineScope(IO).launch {
                val result = ApiClient.getClient.getSearchResultWith3CheckboxBeginDateEndDate(
                    query = query.toString(),
                    category1 = category1.toString(),
                    category2 = category2.toString(),
                    category3 = category3.toString(),
                    begin_date = apiCallDateFormat.toString(),
                    end_date = apiCallDateFormat.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )

//                    val lastNumberOfCurrentTime = currentTime.subSequence(9,10)
//                val dateFromApiCall = "${ result.response.docs.filter { it.multimedia.isNotEmpty() }.toString()
//                    .substringAfter("url=images/").subSequence(0, 9).toString()}" + "$lastNumberOfCurrentTime"
                if (!result.response.docs.isNullOrEmpty()) {
                    withContext(Main) {
                        val editor = sharedPreferences.edit()
                        editor.apply {
                            putString("QUERYSEARCH", query)
                            putString("CATEGORY1SEARCH", category1)
                            putString("CATEGORY2SEARCH", category2)
                            putString("CATEGORY3SEARCH", category3)
                            putString("BEGINDATEANDENDDATESEARCH", apiCallDateFormat.toString())
                        }.apply()
                        val contentIntent = PendingIntent.getActivity(
                            context, 0,
                            Intent(context, NotifiedSearchedNews::class.java), 0
                        )
                        val builder = NotificationCompat.Builder(context, "NotifySearch")
                            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                            .setContentTitle("New articles today from your previous search!")
                            .setContentText("New Articles have been published today in $query - $category1, $category2 and $category3")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setColor(Color.parseColor("#17B6C8"))

                        builder.setContentIntent(contentIntent)
                        builder.setDefaults(Notification.DEFAULT_SOUND)
                        builder.setAutoCancel(true)
                        val notificationManager =
                            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.notify(300, builder.build())
                    }
                    //

//                    Log.d("FromReceiver", "number of last digit in current time : $lastNumberOfCurrentTime")
//                    Log.d(
//                        "FromReceiver","date from api call in images: ${ result.response.docs.filter { it.multimedia.isNotEmpty() }.toString()
//                            .substringAfter("url=images/").subSequence(0, 9).toString()}$lastNumberOfCurrentTime"
//                    )
//                    Log.d("FromReceiver","current time $currentTime" )

                }

            }

        } else if (query != null && category1 != null && category2 != null && category3 != null &&
            category4 != null && category5 == null && category6 == null
        ) {
            CoroutineScope(IO).launch {
                val result = ApiClient.getClient.getSearchResultWith4CheckboxBeginDateEndDate(
                    query = query.toString(),
                    category1 = category1.toString(),
                    category2 = category2.toString(),
                    category3 = category3.toString(),
                    category4 = category4.toString(),
                    begin_date = apiCallDateFormat.toString(),
                    end_date = apiCallDateFormat.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )

//                    val lastNumberOfCurrentTime = currentTime.subSequence(9,10)
//                val dateFromApiCall = "${ result.response.docs.filter { it.multimedia.isNotEmpty() }.toString()
//                    .substringAfter("url=images/").subSequence(0, 9).toString()}" + "$lastNumberOfCurrentTime"
                if (!result.response.docs.isNullOrEmpty()) {
                    withContext(Main) {
                        val editor = sharedPreferences.edit()
                        editor.apply {
                            putString("QUERYSEARCH", query)
                            putString("CATEGORY1SEARCH", category1)
                            putString("CATEGORY2SEARCH", category2)
                            putString("CATEGORY3SEARCH", category3)
                            putString("CATEGORY4SEARCH", category4)
                            putString("BEGINDATEANDENDDATESEARCH", apiCallDateFormat.toString())
                        }.apply()
                        val contentIntent = PendingIntent.getActivity(
                            context, 0,
                            Intent(context, NotifiedSearchedNews::class.java), 0
                        )
                        val builder = NotificationCompat.Builder(context, "NotifySearch")
                            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                            .setContentTitle("New articles today from your previous search!")
                            .setContentText("New Articles have been published today in $query - $category1, $category2, $category3 and $category4")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setColor(Color.parseColor("#17B6C8"))

                        builder.setContentIntent(contentIntent)
                        builder.setDefaults(Notification.DEFAULT_SOUND)
                        builder.setAutoCancel(true)
                        val notificationManager =
                            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.notify(300, builder.build())
                    }
                    //

//                    Log.d("FromReceiver", "number of last digit in current time : $lastNumberOfCurrentTime")
//                    Log.d(
//                        "FromReceiver","date from api call in images: ${ result.response.docs.filter { it.multimedia.isNotEmpty() }.toString()
//                            .substringAfter("url=images/").subSequence(0, 9).toString()}$lastNumberOfCurrentTime"
//                    )
//                    Log.d("FromReceiver","current time $currentTime" )

                }

            }

        } else if (query != null && category1 != null && category2 != null && category3 != null &&
            category4 != null && category5 != null && category6 == null
        ) {
            CoroutineScope(IO).launch {
                val result = ApiClient.getClient.getSearchResultWith5CheckboxBeginDateEndDate(
                    query = query.toString(),
                    category1 = category1.toString(),
                    category2 = category2.toString(),
                    category3 = category3.toString(),
                    category4 = category4.toString(),
                    category5 = category5.toString(),
                    begin_date = apiCallDateFormat.toString(),
                    end_date = apiCallDateFormat.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )

//                    val lastNumberOfCurrentTime = currentTime.subSequence(9,10)
//                val dateFromApiCall = "${ result.response.docs.filter { it.multimedia.isNotEmpty() }.toString()
//                    .substringAfter("url=images/").subSequence(0, 9).toString()}" + "$lastNumberOfCurrentTime"
                if (!result.response.docs.isNullOrEmpty()) {
                    withContext(Main) {
                        val editor = sharedPreferences.edit()
                        editor.apply {
                            putString("QUERYSEARCH", query)
                            putString("CATEGORY1SEARCH", category1)
                            putString("CATEGORY2SEARCH", category2)
                            putString("CATEGORY3SEARCH", category3)
                            putString("CATEGORY4SEARCH", category4)
                            putString("CATEGORY5SEARCH", category5)
                            putString("BEGINDATEANDENDDATESEARCH", apiCallDateFormat.toString())
                        }.apply()
                        val contentIntent = PendingIntent.getActivity(
                            context, 0,
                            Intent(context, NotifiedSearchedNews::class.java), 0
                        )
                        val builder = NotificationCompat.Builder(context, "NotifySearch")
                            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                            .setContentTitle("New articles today from your previous search!")
                            .setContentText("New Articles have been published today in $query - $category1, $category2, $category3, $category4 and $category5")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setColor(Color.parseColor("#17B6C8"))

                        builder.setContentIntent(contentIntent)
                        builder.setDefaults(Notification.DEFAULT_SOUND)
                        builder.setAutoCancel(true)
                        val notificationManager =
                            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.notify(300, builder.build())
                    }
                    //

//                    Log.d("FromReceiver", "number of last digit in current time : $lastNumberOfCurrentTime")
//                    Log.d(
//                        "FromReceiver","date from api call in images: ${ result.response.docs.filter { it.multimedia.isNotEmpty() }.toString()
//                            .substringAfter("url=images/").subSequence(0, 9).toString()}$lastNumberOfCurrentTime"
//                    )
//                    Log.d("FromReceiver","current time $currentTime" )

                }

            }

        } else if (query != null && category1 != null && category2 != null && category3 != null &&
            category4 != null && category5 != null && category6 != null
        ) {
            CoroutineScope(IO).launch {
                val result = ApiClient.getClient.getSearchResultWith6CheckboxBeginDateEndDate(
                    query = query.toString(),
                    category1 = category1.toString(),
                    category2 = category2.toString(),
                    category3 = category3.toString(),
                    category4 = category4.toString(),
                    category5 = category5.toString(),
                    category6 = category6.toString(),
                    begin_date = apiCallDateFormat.toString(),
                    end_date = apiCallDateFormat.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )

//                            val lastNumberOfCurrentTime = currentTime.subSequence(9,10)
//                        val dateFromApiCall = "${ result.response.docs.filter { it.multimedia.isNotEmpty() }.toString()
//                            .substringAfter("url=images/").subSequence(0, 9).toString()}" + "$lastNumberOfCurrentTime"
                if (!result.response.docs.isNullOrEmpty()) {
                    withContext(Main) {
                        val editor = sharedPreferences.edit()
                        editor.apply {
                            putString("QUERYSEARCH", query)
                            putString("CATEGORY1SEARCH", category1)
                            putString("CATEGORY2SEARCH", category2)
                            putString("CATEGORY3SEARCH", category3)
                            putString("CATEGORY4SEARCH", category4)
                            putString("CATEGORY5SEARCH", category5)
                            putString("CATEGORY6SEARCH", category6)
                            putString("BEGINDATEANDENDDATESEARCH", apiCallDateFormat.toString())
                        }.apply()
                        val contentIntent = PendingIntent.getActivity(
                            context, 0,
                            Intent(context, NotifiedSearchedNews::class.java), 0
                        )
                        val builder = NotificationCompat.Builder(context, "NotifySearch")
                            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                            .setContentTitle("New articles today from your previous search!")
                            .setContentText("New Articles have been published today in $query - $category1, $category2, $category3, $category4, $category5 and $category6")
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





        setAlarm(context)
    }

    private fun setAlarm(context: Context) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver2::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        // Setting the specific time for the alarm manager to trigger the intent, here, the alarm is going off at 11:59 everyday
//        val calendar = Calendar.getInstance()
//        calendar.timeInMillis = System.currentTimeMillis()
//        calendar.set(Calendar.HOUR,6)
//        calendar.set(Calendar.MINUTE,2)
//        calendar.set(Calendar.SECOND,10)

        var firstTime = SystemClock.elapsedRealtime()
        firstTime += (10 * 1000).toLong()

//         Starts the alarm manager
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + 86400000,
            pendingIntent
        )
    }
//    private fun showNotification(context:Context) {
////        Log.d("amer", "from notification function in BroadcastReceiver")
//        val contentIntent = PendingIntent.getActivity(context, 0,
//            Intent(context, SearchActivity::class.java), 0)
//        val builder = NotificationCompat.Builder(context, "amer" )
//            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
//            .setContentTitle("My notification")
//            .setContentText("Hello World!")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//        builder.setContentIntent(contentIntent)
//        builder.setDefaults(Notification.DEFAULT_SOUND)
//        builder.setAutoCancel(true)
//        val  notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.notify(200, builder.build())
//    }


}
