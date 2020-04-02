package com.amercosovic.mynews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amercosovic.mynews.model.News
import com.amercosovic.mynews.model.NewsResponse
import com.amercosovic.mynews.retrofit.ApiClient
import com.amercosovic.mynews.retrofit.ApiClient.getClient
import com.amercosovic.mynews.retrofit.ApiInterface
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Dispatchers.Unconfined
import retrofit2.*
import java.io.IOException

//CoroutineScope(IO).launch {
//    val result = getData()
//    textview1.text = result.body.toString()
//}



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(IO).launch {
                try {
                    val response = getData()
                    textview1.text = response.toString()
                } catch (e: HttpException) {
                    textview1.text = e.toString()
                }
                }

            }

       suspend private fun getData() {
            val call: List<NewsResponse> = ApiClient.getClient.getTopStories("G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")
            call.enqueue(object : Callback<NewsResponse> {

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    Log.d("amer", "${response.body()}")
                    Toast.makeText(this@MainActivity,"API CALL IS SUCESSFULL", Toast.LENGTH_LONG).show()
                    textview1.text = response.body().toString()
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message,Toast.LENGTH_LONG).show()
                }
            })
        }
        }



//


