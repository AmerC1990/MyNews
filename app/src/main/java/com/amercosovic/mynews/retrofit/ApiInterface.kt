package com.amercosovic.mynews.retrofit

import com.amercosovic.dummynews2.modelwithmandatorysearchonly.MandatorySearchOnly
import com.amercosovic.mynews.model.MostPopular
import com.amercosovic.mynews.model.NewsResponse
import com.amercosovic.mynews.model.Sports
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    // For the 3 tabs
    @GET("svc/topstories/v2/home.json")
    suspend fun getTopNews(@Query("api-key") api_key: String): NewsResponse

    @GET("svc/mostpopular/v2/viewed/1.json")
    suspend fun getPopularNews(@Query("api-key") api_key: String): MostPopular

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSports(@Query("q") query: String, @Query("api-key") api_key: String): Sports

    // For the results of the Search Articles
    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResult(
        @Query("q", encoded = false) query: String ,
        @Query("api-key") api_key: String
    ): MandatorySearchOnly

    // For the Notifications
    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultForNotification(
        @Query("q", encoded = false) query: String ,
        @Query("begin_date") begin_date: String,
        @Query("end_date") end_date: String,
        @Query("api-key") api_key: String
    ): MandatorySearchOnly
}