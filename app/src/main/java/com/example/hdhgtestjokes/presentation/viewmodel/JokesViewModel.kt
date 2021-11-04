package com.example.hdhgtestjokes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hdhgtestjokes.AndroidApplication
import com.example.hdhgtestjokes.R
import com.example.hdhgtestjokes.domain.Joke
import com.example.hdhgtestjokes.domain.usecase.GetJokeListUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class JokesViewModel(private val useCase: GetJokeListUseCase) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val mCountValue = MutableLiveData<Int?>()
    val countValue: LiveData<Int?> = mCountValue

    private val mJokeList = MutableLiveData<List<Joke?>?>()
    val jokeList: LiveData<List<Joke?>?> = mJokeList

    fun reload(number: Int?) {
        if (number!= null && number!= 0) {
            mCountValue.value = number!!
            val disposable = useCase.getJokes(mCountValue.value)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(Schedulers.computation())
                ?.subscribe { jokes ->
                    mJokeList.postValue(jokes)
                    mCountValue.postValue(null)
                }
            disposable?.let { compositeDisposable.add(disposable) }
        }

    }

}