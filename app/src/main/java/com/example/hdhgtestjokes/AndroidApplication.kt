package com.example.hdhgtestjokes

import android.app.Application
import com.example.hdhgtestjokes.data.api.JokeApiService
import com.example.hdhgtestjokes.data.repository.JokeRepositoryImpl
import com.example.hdhgtestjokes.data.repository.RemoteDataSource
import com.example.hdhgtestjokes.di.AppModule
import com.example.hdhgtestjokes.di.NetworkModule
import com.example.hdhgtestjokes.domain.usecase.GetJokeListUseCase
import com.example.hdhgtestjokes.presentation.viewmodel.ViewModelFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AndroidApplication : Application() {

    companion object {
        lateinit var instance: AndroidApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AndroidApplication)
            modules(listOf(AppModule, NetworkModule))
        }
    }
}
