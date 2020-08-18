package com.amercosovic.mynews

import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val bundle: Bundle? = intent.extras
        val msg = bundle?.getString("url")


        activitySecondWebView.loadUrl(msg)

        activitySecondWebView.setWebViewClient(object: WebViewClient() {
            override fun onPageCommitVisible(view: WebView?, url: String?) {
                pbarInActivitySecond.isVisible = false
            }})


        actionBar?.setDisplayHomeAsUpEnabled(true)

    }
}
