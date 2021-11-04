package com.example.hdhgtestjokes.data.entities

import com.google.gson.annotations.SerializedName

class JSCountData (
    @SerializedName("type")
    val result: String,
    @SerializedName("value")
    val count: Int
)