package com.example.hdhgtestjokes.data.entities

import com.google.gson.annotations.SerializedName

class JSData (
    val result: String,
    @SerializedName("value")
    val jokes: List<JokeEntity?>?
)