package com.amercosovic.mynews

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amercosovic.mynews.adapters.NotifiedNewsAdapter
import com.amercosovic.mynews.retrofit.ApiClient
import kotlinx.android.synthetic.main.activity_notified_news.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotifiedNews : AppCompatActivity() {

    private lateinit var editor: SharedPreferences.Editor
    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notified_news)


        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val query = sharedPreferences?.getString("QUERY", null)
        val beginAndEndDate = sharedPreferences?.getString("BEGINDATEANDENDDATE", null)

        if (query != null ) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultForNotification(
                    query = query,
                    begin_date = beginAndEndDate.toString(),
                    end_date = beginAndEndDate.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )
                withContext(Main) {
                    pbarInNotifiedNews.isVisible = false
                    notifiedNewsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@NotifiedNews)
                        adapter = NotifiedNewsAdapter(result.response.docs)
                    }
                }
            }

        }
    }

}