package com.example.hdhgtestjokes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hdhgtestjokes.domain.usecase.GetJokeListUseCase

class ViewModelFactory( private val useCase: GetJokeListUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokesViewModel::class.java)) {
            return JokesViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}