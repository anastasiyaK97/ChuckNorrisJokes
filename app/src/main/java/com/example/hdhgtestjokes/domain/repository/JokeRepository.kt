package com.example.hdhgtestjokes.domain.repository

import com.example.hdhgtestjokes.domain.Joke
import io.reactivex.Observable

interface JokeRepository {
    fun jokes(number: Int?): Observable<List<Joke?>?>?
}