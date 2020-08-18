package com.amercosovic.mynews

import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_read_notified_article.*
import kotlinx.android.synthetic.main.activity_read_notified_searched_article.*
import kotlinx.android.synthetic.main.activity_second.*

class ReadNotifiedSearchedArticle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_notified_searched_article)

        val bundle: Bundle? = intent.extras
        val msg = bundle?.getString("url")

        readNotifiedSearchedArticleWebview.loadUrl(msg)

        readNotifiedSearchedArticleWebview.setWebViewClient(object: WebViewClient() {
            override fun onPageCommitVisible(view: WebView?, url: String?) {
                pbarInReadNotifiedSearchedArticleActivity.isVisible = false
            }})

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}