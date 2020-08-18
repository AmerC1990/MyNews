package com.amercosovic.mynews.retrofit

import android.net.Uri
import com.amercosovic.dummynews2.modelwithmandatorysearchonly.MandatorySearchOnly
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

internal class MyInterceptor: Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain:Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        var stringurl = request.url.toString()
        stringurl = stringurl.replace("%26", "&").replace("%3D","=")
        val newRequest = Request.Builder()
            .url(stringurl)
            .build()
        return chain.proceed(newRequest)
    }
}

object ApiClient {
    //    var BASE_URL:String="https://api.nytimes.com/svc/topstories/v2/home.json?api-key=PFkuFrdSjRPRWnI0JSdnWOArzr5GuFZE"
    var BASE_URL: String = "https://api.nytimes.com/"
    val getClient: ApiInterface
        get() {

            val gson = GsonBuilder().setLenient().create()
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(MyInterceptor()).build()


            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()



            return retrofit.create(ApiInterface::class.java)


        }
}


