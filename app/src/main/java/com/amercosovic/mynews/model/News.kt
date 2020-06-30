package com.amercosovic.mynews.model

import com.google.gson.annotations.SerializedName


data class News (

    @SerializedName("abstract")
    val `abstract`: String,
    @SerializedName("byline")
    val byline: String,
    @SerializedName("item_type")
    val itemType: String,
    @SerializedName("kicker")
    val kicker: String,
    @SerializedName("material_type_facet")
    val materialTypeFacet: String,
    @SerializedName("multimedia")
    val multimedia: List<Multimedia>,
    @SerializedName("published_date")
    val publishedDate: String,
    @SerializedName("section")
    val section: String,
    @SerializedName("short_url")
    val shortUrl: String,
    @SerializedName("subsection")
    val subsection: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("url")
    val url: String

)








