package com.example.hdhgtestjokes.data.entities.mapper

import com.example.hdhgtestjokes.data.entities.JokeEntity
import com.example.hdhgtestjokes.domain.Joke

class JokeEntityDataMapper {
    fun transform(jokeEntity: JokeEntity?): Joke? {
        var joke: Joke? = null
        if (jokeEntity != null) {
            joke = Joke(jokeEntity.id).apply {
                setText(jokeEntity.text)
                setCategories(jokeEntity.categories)
            }
        }
        return joke
    }

}