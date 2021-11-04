package com.example.hdhgtestjokes.data.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.hdhgtestjokes.AndroidApplication

interface InternetConnectionCheckable {
    fun isInternetAvailable(): Boolean {
        var available = false
        val context = AndroidApplication.instance
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivity != null) {
            val networkInfo = connectivity.allNetworkInfo
            if (networkInfo != null) {
                for (i in networkInfo.indices) {
                    if (networkInfo[i].state == NetworkInfo.State.CONNECTED) {
                        available = true
                        break
                    }
                }
            }
        }
        return available
    }
}