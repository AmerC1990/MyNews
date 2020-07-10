package com.amercosovic.mynews.model


import com.google.gson.annotations.SerializedName

data class Sports(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("response")
    val response: Response,
    @SerializedName("status")
    val status: String
)