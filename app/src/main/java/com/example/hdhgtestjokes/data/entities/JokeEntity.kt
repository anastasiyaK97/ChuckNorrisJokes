package com.example.hdhgtestjokes.data.entities

import com.google.gson.annotations.SerializedName

class JokeEntity (
    @SerializedName("id")
    val id: Int,
    @SerializedName("joke")
    val text: String?,
    @SerializedName("categories")
    val categories: List<String>?
)