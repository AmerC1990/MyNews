package com.amercosovic.mynews.model

import com.google.gson.annotations.SerializedName


data class News (

    @SerializedName("title")
    val newsTitle: String = "",

    @SerializedName("abstract")
    val newsAbstract: String ="",

    @SerializedName("url")
    val newsUrl: String = "",

    @SerializedName("publishedDate")
    val newsPublishedDate: Long = System.currentTimeMillis()

)