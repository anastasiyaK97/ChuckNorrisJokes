package com.example.hdhgtestjokes.domain

class Joke(private val id: Int) {
    private var text: String? = null
    private var categories: List<String?>? = null

    fun setText(text: String?) {
        this.text = text
    }

    fun getText() = this.text

    fun setCategories(categories: List<String?>?) {
        this.categories = categories
    }

}