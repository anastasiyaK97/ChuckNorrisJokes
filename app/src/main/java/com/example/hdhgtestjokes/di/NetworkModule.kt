package com.example.hdhgtestjokes.di

import com.example.hdhgtestjokes.BuildConfig
import com.example.hdhgtestjokes.Constants
import com.example.hdhgtestjokes.data.api.JokeApiService
import com.example.hdhgtestjokes.data.repository.RemoteDataSource
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {
    single { createService(get()) }
    single { createRetrofit(Constants.BASE_URL, get()) }
    single { okHttpClient(get()) }
    single { httpLoggingInterceptor() }

}
private fun httpLoggingInterceptor()  = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.BASIC
}
private fun okHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    val okBuilder = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
        addInterceptor { chain ->
            return@addInterceptor chain.proceed(
                chain
                    .request()
                    .newBuilder()
                    .addHeader("accept", "application/json")
                    .build()
            )
        }
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
    }

   return okBuilder.build()
}
private fun createRetrofit(baseUrl: String, okHttpClient: OkHttpClient) =  Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

fun createService(retrofit: Retrofit): JokeApiService =
    retrofit.create(JokeApiService::class.java)

fun createDataSource(jokeApiService: JokeApiService): RemoteDataSource =
    RemoteDataSource(jokeApiService)