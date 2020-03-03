package com.amercosovic.mynews.retrofit

import com.amercosovic.mynews.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("home.json?")
    fun getTopStories(@Query("api-key")key:String): Call<List<News>>
}