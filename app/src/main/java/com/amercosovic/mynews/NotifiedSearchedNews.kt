package com.amercosovic.mynews

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        val category1 = sharedPreferences?.getString("CATEGORY1SEARCH", null)
        val category2 = sharedPreferences?.getString("CATEGORY2SEARCH", null)
        val category3 = sharedPreferences?.getString("CATEGORY3SEARCH", null)
        val category4 = sharedPreferences?.getString("CATEGORY4SEARCH", null)
        val category5 = sharedPreferences?.getString("CATEGORY5SEARCH", null)
        val category6 = sharedPreferences?.getString("CATEGORY6SEARCH", null)
        val beginAndEndDate = sharedPreferences?.getString("BEGINDATEANDENDDATESEARCH", null)

        if (query != null && category1 != null && category2 == null && category3 == null &&
            category4 == null && category5 == null && category6 == null
        ) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith1CheckboxBeginDateEndDate(
                    query = query,
                    category1 = category1,
                    begin_date = beginAndEndDate.toString(),
                    end_date = beginAndEndDate.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )
                withContext(Main) {
                    NotifiedSearchedNewsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@NotifiedSearchedNews)
                        adapter = NotifiedSearchedNewsAdapter(result.response.docs)
                    }
                }
            }

        } else if (query != null && category1 != null && category2 != null && category3 == null &&
            category4 == null && category5 == null && category6 == null
        ) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith2CheckboxBeginDateEndDate(
                    query = query,
                    category1 = category1,
                    category2 = category2,
                    begin_date = beginAndEndDate.toString(),
                    end_date = beginAndEndDate.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )
                withContext(Main) {
                    NotifiedSearchedNewsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@NotifiedSearchedNews)
                        adapter = NotifiedSearchedNewsAdapter(result.response.docs)
                    }
                }
            }
        } else if (query != null && category1 != null && category2 != null && category3 != null &&
            category4 == null && category5 == null && category6 == null
        ) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith3CheckboxBeginDateEndDate(
                    query = query,
                    category1 = category1,
                    category2 = category2,
                    category3 = category3,
                    begin_date = beginAndEndDate.toString(),
                    end_date = beginAndEndDate.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )
                withContext(Main) {
                    NotifiedSearchedNewsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@NotifiedSearchedNews)
                        adapter = NotifiedSearchedNewsAdapter(result.response.docs)
                    }
                }
            }
        } else if (query != null && category1 != null && category2 != null && category3 != null &&
            category4 != null && category5 == null && category6 == null
        ) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith4CheckboxBeginDateEndDate(
                    query = query,
                    category1 = category1,
                    category2 = category2,
                    category3 = category3,
                    category4 = category4,
                    begin_date = beginAndEndDate.toString(),
                    end_date = beginAndEndDate.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )
                withContext(Main) {
                    NotifiedSearchedNewsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@NotifiedSearchedNews)
                        adapter = NotifiedSearchedNewsAdapter(result.response.docs)
                    }
                }
            }
        } else if (query != null && category1 != null && category2 != null && category3 != null &&
            category4 != null && category5 != null && category6 == null
        ) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith5CheckboxBeginDateEndDate(
                    query = query,
                    category1 = category1,
                    category2 = category2,
                    category3 = category3,
                    category4 = category4,
                    category5 = category5,
                    begin_date = beginAndEndDate.toString(),
                    end_date = beginAndEndDate.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )
                withContext(Main) {
                    NotifiedSearchedNewsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@NotifiedSearchedNews)
                        adapter = NotifiedSearchedNewsAdapter(result.response.docs)
                    }
                }
            }
        } else if (query != null && category1 != null && category2 != null && category3 != null &&
            category4 != null && category5 != null && category6 != null
        ) {
            lifecycleScope.launch(IO) {
                val result = ApiClient.getClient.getSearchResultWith6CheckboxBeginDateEndDate(
                    query = query,
                    category1 = category1,
                    category2 = category2,
                    category3 = category3,
                    category4 = category4,
                    category5 = category5,
                    category6 = category6,
                    begin_date = beginAndEndDate.toString(),
                    end_date = beginAndEndDate.toString(),
                    api_key = "G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
                )
                withContext(Main) {
                    NotifiedSearchedNewsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@NotifiedSearchedNews)
                        adapter = NotifiedSearchedNewsAdapter(result.response.docs)
                    }
                }
            }

        }
    }

}
