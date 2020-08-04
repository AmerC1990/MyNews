package com.amercosovic.mynews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class ReadNotifiedArticle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_notified_article)

        val bundle: Bundle? = intent.extras
        val msg = bundle?.getString("url")
        webView.loadUrl(msg)

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}