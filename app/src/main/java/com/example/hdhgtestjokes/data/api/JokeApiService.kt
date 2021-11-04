package com.example.hdhgtestjokes.data.api

import com.example.hdhgtestjokes.data.entities.JSCountData
import com.example.hdhgtestjokes.data.entities.JSData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface JokeApiService {
   
    @GET("jokes/random/{number}")
    fun getSomeJokes(
        @Path("number") num: Int?
    ): Single<JSData>
}
