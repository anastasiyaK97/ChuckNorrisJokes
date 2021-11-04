package com.example.hdhgtestjokes.di

import com.example.hdhgtestjokes.data.repository.JokeRepositoryImpl
import com.example.hdhgtestjokes.data.repository.RemoteDataSource
import com.example.hdhgtestjokes.domain.repository.JokeRepository
import com.example.hdhgtestjokes.domain.usecase.GetJokeListUseCase
import com.example.hdhgtestjokes.presentation.viewmodel.ViewModelFactory
import org.koin.dsl.module

val AppModule = module {
    single { createDataSource(get()) }
    single { createRepository(get()) }
    single { createGetJokeListUseCase(get()) }
    single { createViewModelFactory(get()) }
}
fun createRepository(remoteDataSource: RemoteDataSource): JokeRepository =
    JokeRepositoryImpl (remoteDataSource)

fun createGetJokeListUseCase(repository: JokeRepository) =
    GetJokeListUseCase(repository)

fun createViewModelFactory(useCase: GetJokeListUseCase) =
    ViewModelFactory(useCase)