package com.amercosovic.mynews

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amercosovic.mynews.retrofit.ApiClient.getClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.android.synthetic.main.news_row.view.*


val TAG = "Check"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        1st way of doing this
        lifecycleScope.launch(IO) {
            getNews()
        }

//        2nd way of doing this (COMMENT 1st way and then uncomment the below to see it)
//        lifecycleScope.launch {
//            textview1.text = getNewsByAsync().toString()
    }


    //1st way of doing this
     suspend fun getNews() {
        val newsResponse = getClient.getTopStories("G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")
        Log.d("amer", "${newsResponse.results.size}")

        withContext(Main) {
//            Toast.makeText(this@MainActivity, "API CALL IS SUCCESSFUL", Toast.LENGTH_LONG).show()
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = NewsAdapter(newsResponse.results)
            }

            }
        }
    }


        //
//
//
//        }
//    }
//    2nd way of doing this
//        private suspend fun getNewsByAsync(): NewsResponse {
//            return lifecycleScope.async(IO) { getClient.getTopStories("G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")}
//                .await()
//        }
//    }



