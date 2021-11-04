package com.example.hdhgtestjokes.domain.usecase

import com.example.hdhgtestjokes.domain.Joke
import com.example.hdhgtestjokes.domain.repository.JokeRepository
import io.reactivex.Observable

class GetJokeListUseCase (private val jokeRepository: JokeRepository){
    fun getJokes(count : Int?): Observable<List<Joke?>?>? {
        return jokeRepository.jokes(count)
    }
}