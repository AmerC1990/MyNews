package com.amercosovic.mynews.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("results")
    val results: List<News>
)