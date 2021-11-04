package com.example.hdhgtestjokes.presentation.viewmodel

import android.content.Context
import android.view.ViewGroup
import android.webkit.WebView
import androidx.lifecycle.LiveData

private const val URL = "https://www.icndb.com/api/"

class WebViewLiveData(context: Context): LiveData<WebView>() {
    private val webView: WebView = WebView(context).apply {
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.builtInZoomControls = true
        settings.displayZoomControls = false

        loadUrl(URL)
    }

    override fun onActive() {
        value = webView
    }

    override fun onInactive() {
        webView.detachFromParent()
    }

    private fun WebView.detachFromParent() {
        (parent as? ViewGroup)?.removeView(this)
    }
}