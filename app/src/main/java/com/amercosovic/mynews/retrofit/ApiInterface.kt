package com.amercosovic.mynews.retrofit

import com.amercosovic.dummynews2.modelwithmandatorysearchonly.MandatorySearchOnly
import com.amercosovic.dummynews2.modelwithmandatorysearchonly.Response
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
    // For the 3 tabs
    @GET("svc/topstories/v2/home.json")
    suspend fun getTopNews(@Query("api-key") api_key: String): NewsResponse

    @GET("svc/mostpopular/v2/viewed/1.json")
    suspend fun getPopularNews(@Query("api-key") api_key: String): MostPopular

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSports(@Query("q") query: String, @Query("api-key") api_key: String ): Sports

    // For the results of the Search Articles

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith1Checkbox(@Query("q") query: String, @Query("fq")category1: String,
                                             @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith2Checkbox(@Query("q") query: String, @Query("fq")category1: String,
                                             @Query("fq")category2: String,
                                             @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith3Checkbox(@Query("q") query: String, @Query("fq")category1: String,
                                             @Query("fq")category2: String,@Query("fq")category3: String,
                                             @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith4Checkbox(@Query("q") query: String, @Query("fq")category1: String,
                                             @Query("fq")category2: String,@Query("fq")category3: String,
                                             @Query("fq")category4: String,
                                             @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith5Checkbox(@Query("q") query: String, @Query("fq")category1: String,
                                             @Query("fq")category2: String,@Query("fq")category3: String,
                                             @Query("fq")category4: String,@Query("fq")category5: String,
                                             @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith6Checkbox(@Query("q") query: String, @Query("fq")category1: String,
                                             @Query("fq")category2: String,@Query("fq")category3: String,
                                             @Query("fq")category4: String,@Query("fq")category5: String,
                                             @Query("fq")category6: String,
                                             @Query("api-key") api_key: String ): MandatorySearchOnly
    //
    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith1CheckboxAndBeginDate(@Query("q") query: String,@Query("begin_date") begin_date: String,
                                                         @Query("fq")category1: String,
                                                 @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith2CheckboxAndBeginDate(@Query("q") query: String,@Query("begin_date") begin_date: String,
                                                         @Query("fq")category1: String,@Query("fq")category2: String,
                                                         @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith3CheckboxAndBeginDate(@Query("q") query: String,@Query("begin_date") begin_date: String,
                                                         @Query("fq")category1: String,@Query("fq")category2: String,
                                                         @Query("fq")category3: String,
                                                         @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith4CheckboxAndBeginDate(@Query("q") query: String,@Query("begin_date") begin_date: String,
                                                         @Query("fq")category1: String,@Query("fq")category2: String,
                                                         @Query("fq")category3: String,@Query("fq")category4: String,
                                                         @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith5CheckboxAndBeginDate(@Query("q") query: String,@Query("begin_date") begin_date: String,
                                                         @Query("fq")category1: String,@Query("fq")category2: String,
                                                         @Query("fq")category3: String,@Query("fq")category4: String,
                                                         @Query("fq")category5: String,
                                                         @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith6CheckboxAndBeginDate(@Query("q") query: String,@Query("begin_date") begin_date: String,
                                                         @Query("fq")category1: String,@Query("fq")category2: String,
                                                         @Query("fq")category3: String,@Query("fq")category4: String,
                                                         @Query("fq")category5: String,@Query("fq")category6: String,
                                                         @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith1CheckboxAndEndDate(@Query("q") query: String,@Query("end_date") end_date: String,
                                                         @Query("fq")category1: String,
                                                         @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith2CheckboxAndEndDate(@Query("q") query: String,@Query("end_date") end_date: String,
                                                       @Query("fq")category1: String, @Query("fq")category2: String,
                                                       @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith3CheckboxAndEndDate(@Query("q") query: String,@Query("end_date") end_date: String,
                                                       @Query("fq")category1: String, @Query("fq")category2: String,
                                                       @Query("fq")category3: String,
                                                       @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith4CheckboxAndEndDate(@Query("q") query: String,@Query("end_date") end_date: String,
                                                       @Query("fq")category1: String, @Query("fq")category2: String,
                                                       @Query("fq")category3: String,@Query("fq")category4: String,
                                                       @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith5CheckboxAndEndDate(@Query("q") query: String,@Query("end_date") end_date: String,
                                                       @Query("fq")category1: String, @Query("fq")category2: String,
                                                       @Query("fq")category3: String,@Query("fq")category4: String,
                                                       @Query("fq")category5: String,
                                                       @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith6CheckboxAndEndDate(@Query("q") query: String,@Query("end_date") end_date: String,
                                                       @Query("fq")category1: String, @Query("fq")category2: String,
                                                       @Query("fq")category3: String,@Query("fq")category4: String,
                                                       @Query("fq")category5: String,@Query("fq")category6: String,
                                                       @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith1CheckboxBeginDateEndDate(@Query("q") query: String,@Query("begin_date") begin_date: String,
                                                               @Query("end_date")end_date: String,
                                                               @Query("fq")category1: String, @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith2CheckboxBeginDateEndDate(@Query("q") query: String,@Query("begin_date") begin_date: String,
                                                             @Query("end_date")end_date: String,
                                                             @Query("fq")category1: String, @Query("fq")category2: String,
                                                             @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith3CheckboxBeginDateEndDate(@Query("q") query: String,@Query("begin_date") begin_date: String,
                                                             @Query("end_date")end_date: String,
                                                             @Query("fq")category1: String, @Query("fq")category2: String,
                                                             @Query("fq")category3: String,
                                                             @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith4CheckboxBeginDateEndDate(@Query("q") query: String,@Query("begin_date") begin_date: String,
                                                             @Query("end_date")end_date: String,
                                                             @Query("fq")category1: String, @Query("fq")category2: String,
                                                             @Query("fq")category3: String,@Query("fq")category4: String,
                                                             @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith5CheckboxBeginDateEndDate(@Query("q") query: String,@Query("begin_date") begin_date: String,
                                                             @Query("end_date")end_date: String,
                                                             @Query("fq")category1: String, @Query("fq")category2: String,
                                                             @Query("fq")category3: String,@Query("fq")category4: String,
                                                             @Query("fq")category5: String,
                                                             @Query("api-key") api_key: String ): MandatorySearchOnly

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getSearchResultWith6CheckboxBeginDateEndDate(@Query("q") query: String,@Query("begin_date") begin_date: String,
                                                             @Query("end_date")end_date: String,
                                                             @Query("fq")category1: String, @Query("fq")category2: String,
                                                             @Query("fq")category3: String,@Query("fq")category4: String,
                                                             @Query("fq")category5: String,@Query("fq")category6: String,
                                                             @Query("api-key") api_key: String ): MandatorySearchOnly

}


// SPORTS
//https://api.nytimes.com/svc/search/v2/articlesearch.json?q=sports&api-key=G9Xfi28dQn57YSw4gz11Smt0eBZumn6m

//MOST POPULAR
//https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=G9Xfi28dQn57YSw4gz11Smt0eBZumn6m


// TOP STORIES
//var BASE_URL:String="https://api.nytimes.com/svc/topstories/v2/home.json?api-key=G9Xfi28dQn57YSw4gz11Smt0eBZumn6m"
