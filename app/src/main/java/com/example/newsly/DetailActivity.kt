package com.example.newsly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.webViewTheme)
        setContentView(R.layout.activity_detail)

        val url = intent.getStringExtra("URL")
        if(url != null) {
            wvDetail.settings.javaScriptEnabled = true
            wvDetail.settings.userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36"

            wvDetail.webViewClient = object: WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    pbWeb.visibility = View.GONE
                    wvDetail.visibility = View.VISIBLE
                }
            }
            wvDetail.loadUrl(url)
        }
    }
}