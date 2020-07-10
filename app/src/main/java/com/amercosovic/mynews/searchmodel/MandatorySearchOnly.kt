package com.amercosovic.dummynews2.modelwithmandatorysearchonly


import com.google.gson.annotations.SerializedName

data class MandatorySearchOnly(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("response")
    val response: Response,
    @SerializedName("status")
    val status: String
)