package com.example.hdhgtestjokes.data.repository

import com.example.hdhgtestjokes.domain.Joke
import com.example.hdhgtestjokes.domain.repository.JokeRepository
import io.reactivex.Observable

class JokeRepositoryImpl (private val remoteDataSource: RemoteDataSource) : JokeRepository {
    override fun jokes(number: Int?): Observable<List<Joke?>?>? {
        return remoteDataSource.getJokes(number)
    }
}