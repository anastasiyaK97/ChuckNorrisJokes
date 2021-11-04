package com.example.hdhgtestjokes.data.repository

import com.example.hdhgtestjokes.data.api.JokeApiService
import com.example.hdhgtestjokes.data.entities.mapper.JokeEntityDataMapper
import com.example.hdhgtestjokes.domain.Joke
import io.reactivex.Observable

class RemoteDataSource(private val apiService: JokeApiService) {
    private val mapper = JokeEntityDataMapper()

    fun getJokes(number: Int?) : Observable<List<Joke?>?>? {
        return apiService.getSomeJokes(number)
            .flattenAsObservable{ it.jokes }
            .map { mapper.transform(it) }
            .toList()
            .toObservable()
    }
}