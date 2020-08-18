package com.amercosovic.mynews

import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_read_notified_article.*
import kotlinx.android.synthetic.main.activity_second.*

class ReadNotifiedArticle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_notified_article)

        val bundle: Bundle? = intent.extras
        val msg = bundle?.getString("url")

        readNotifiedArticleWebview.loadUrl(msg)

        readNotifiedArticleWebview.setWebViewClient(object: WebViewClient() {
            override fun onPageCommitVisible(view: WebView?, url: String?) {
                pbarInReadNotifiedArticleActivity.isVisible = false
            }})

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}