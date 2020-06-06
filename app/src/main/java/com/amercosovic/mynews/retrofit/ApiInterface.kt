package com.amercosovic.mynews.retrofit

import com.amercosovic.mynews.model.News
import com.amercosovic.mynews.model.NewsResponse
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
   suspend fun getNewsData(@Query("api-key") api_key: String): NewsResponse
}
//https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=G9Xfi28dQn57YSw4gz11Smt0eBZumn6m



//var BASE_URL:String="https://api.nytimes.com/svc/topstories/v2/home.json?api-key=G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
