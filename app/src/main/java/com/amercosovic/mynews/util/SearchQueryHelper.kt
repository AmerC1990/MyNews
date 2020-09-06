package com.amercosovic.mynews.util

import kotlinx.android.synthetic.main.activity_search.*

fun buildQueryForSearchResult(searchQuery: String, categories: List<String>, enterBeginDate: String?, enterEndDate: String?): String {

    var beginDate: String? = "&begin_date=" + enterBeginDate.toString()
        .substringAfterLast("/") + enterBeginDate.toString()?.substringAfter("/")
        ?.substringBefore("/") +
            enterBeginDate.toString()?.substringBefore("/")
    var endDate: String? = "&end_date=" + enterEndDate.toString()
        .substringAfterLast("/") + enterEndDate.toString()?.substringAfter("/")
        ?.substringBefore("/") +
            enterEndDate.toString()?.substringBefore("/")
    if (beginDate?.contains("0") == false) {
        beginDate = ""
    }
    if (endDate?.contains("0") == false) {
        endDate = ""
    }


    var myQuery = searchQuery + beginDate + endDate + categories.toString().replace("[","").replace("]","").replace(",","").replace(" ","")
        .replace("Politics", "&fq=Politics").replace("Business", "&fq=Business").replace("Entrepreneurs","&fq=Entrepreneurs")
        .replace("Arts","&fq=Arts").replace("Travel","&fq=Travel").replace("Sports","&fq=Sports")

    return myQuery
}

fun buildQueryForNotification(searchQuery: String, categories: List<String>): String {

    var myQuery = searchQuery + categories.toString().replace("[","").replace("]","").replace(",","").replace(" ","")
        .replace("Politics", "\u0026fq=Politics").replace("Business", "\u0026fq=Business").replace("Entrepreneurs","\u0026fq=Entrepreneurs")
        .replace("Arts","\u0026fq=Arts").replace("Travel","\u0026fq=Travel").replace("Sports","\u0026fq=Sports")

    return myQuery
}