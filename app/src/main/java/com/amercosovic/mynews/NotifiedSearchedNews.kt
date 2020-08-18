package com.amercosovic.mynews

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amercosovic.mynews.adapters.NotifiedSearchedNewsAdapter
import com.amercosovic.mynews.retrofit.ApiClient
import kotlinx.android.synthetic.main.notified_searched_news.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotifiedSearchedNews : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notified_searched_news)


        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val query = sharedPreferences?.getString("QUERYSEARCH", null)
        val beginAndEndDate = sharedPreferences?.getString("BEGINDATEANDENDDATESEARCH", null)

        if (query != null) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultForNotification(
                    query = query,
                    begin_date = beginAndEndDate.toString(),
                    end_date = beginAndEndDate.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )
                withContext(Main) {
                    pbarInNotifiedSearchedNews.isVisible = false
                    NotifiedSearchedNewsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@NotifiedSearchedNews)
                        adapter = NotifiedSearchedNewsAdapter(result.response.docs)
                    }
                }
            }
        }
            }

        }

