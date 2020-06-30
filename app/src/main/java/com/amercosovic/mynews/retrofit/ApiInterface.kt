package com.amercosovic.mynews.retrofit

import com.amercosovic.mynews.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("svc/topstories/v2/home.json")
    suspend fun getTopNews(@Query("api-key") api_key: String): NewsResponse

    @GET("svc/mostpopular/v2/viewed/1.json")
    suspend fun getPopularNews(@Query("api-key") api_key: String): MostPopular

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSports(@Query("q") query: String, @Query("api-key") api_key: String ): Sports

//    @GET("svc/mostpopular/v2/viewed/1.json")
//    suspend fun getPopularNews(@Query("api-key") api_key: String): NewsResponse
}


// SPORTS
//https://api.nytimes.com/svc/search/v2/articlesearch.json?q=sports&api-key=G9Xfi28dQn57YSw4gz11Smt0eBZumn6m

//MOST POPULAR
//https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=G9Xfi28dQn57YSw4gz11Smt0eBZumn6m


// TOP STORIES
//var BASE_URL:String="https://api.nytimes.com/svc/topstories/v2/home.json?api-key=G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
