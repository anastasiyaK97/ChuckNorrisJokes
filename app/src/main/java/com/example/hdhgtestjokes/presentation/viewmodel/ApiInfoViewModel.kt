package com.example.hdhgtestjokes.presentation.viewmodel

import android.content.Context
import android.webkit.WebView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ApiInfoViewModel(private val appContext: Context) : ViewModel() {
    private val mWebView = WebViewLiveData(appContext)
    val webView: LiveData<WebView> = mWebView

    class Factory(private val appContext: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ApiInfoViewModel::class.java)) {
                return ApiInfoViewModel(appContext) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}