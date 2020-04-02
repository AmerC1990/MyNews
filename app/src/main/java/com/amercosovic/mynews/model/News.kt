package com.amercosovic.mynews.model

import com.google.gson.annotations.SerializedName


data class News (

    @SerializedName("abstract")
    val newsAbstract: String,

    @SerializedName("published_date")
    val newsPublishedDate: String,

    @SerializedName("title")
    val newsTitle: String,

    @SerializedName("url")
    val newsUrl: String

)

data class NewsResponse (

    @SerializedName("results")
    val results: List<News>
)






