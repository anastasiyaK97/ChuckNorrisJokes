package com.example.hdhgtestjokes

import android.app.Application
import com.example.hdhgtestjokes.data.api.JokeApiService
import com.example.hdhgtestjokes.data.repository.JokeRepositoryImpl
import com.example.hdhgtestjokes.data.repository.RemoteDataSource
import com.example.hdhgtestjokes.domain.repository.JokeRepository
import com.example.hdhgtestjokes.domain.usecase.GetJokeListUseCase
import com.example.hdhgtestjokes.presentation.viewmodel.ViewModelFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AndroidApplication : Application() {
    private val BASE_URL = "http://api.icndb.com/"
    lateinit var jokeApiService: JokeApiService
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        lateinit var instance: AndroidApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initApiService()
        viewModelFactory =
            ViewModelFactory(GetJokeListUseCase(JokeRepositoryImpl(RemoteDataSource(jokeApiService))))
    }

    private fun initApiService() {
        val interceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.BASIC
        }

        val okBuilder = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
            addInterceptor { chain ->
                return@addInterceptor chain.proceed(
                    chain
                        .request()
                        .newBuilder()
                        .addHeader("X-API-KEY", "d4c64b7d-d347-4dff-9b1a-109d52f5c27a")
                        .addHeader("accept", "application/json")
                        .build()
                )
            }
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
        }

        val okHttpClient = okBuilder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        jokeApiService = retrofit.create(JokeApiService::class.java)
    }
}