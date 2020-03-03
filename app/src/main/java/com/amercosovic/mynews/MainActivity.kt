package com.amercosovic.mynews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.amercosovic.mynews.model.News
import com.amercosovic.mynews.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
    }


private fun getData() {
    val call: Call<List<News>> = ApiClient.getClient.getTopStories("G9Xfi28dQn57YSw4gz11Smt0eBZumn6m")
    call.enqueue(object : Callback<List<News>> {

        override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
            Log.d("amer","List size is ${response.body()?.size}")

//            dataList.addAll(response!!.body()!!)
        }

        override fun onFailure(call: Call<List<News>>, t: Throwable?) {
            Log.d("amer", "FAILURE")

        }

    })
}
}

