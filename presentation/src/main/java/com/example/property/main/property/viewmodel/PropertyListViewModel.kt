package com.example.property.main.property.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.property.core.Event
import com.example.property.core.SchedulerFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable

class PropertyListViewModel(
    private val schedulerFactory: SchedulerFactory,
) : ViewModel() {
    private val _openDetailsEvent = MutableLiveData<Event<String>>()
    val openDetailsEvent: LiveData<Event<String>> = _openDetailsEvent

    val loadingLiveData = MutableLiveData<Boolean>()
    val mainComicUrlLiveData = MutableLiveData<String>()
    val comicTitleLiveData = MutableLiveData<String>()
    val comicDescriptionLiveData = MutableLiveData<String>()
    val errorLiveData = MutableLiveData<String>()

    private var latestComicId: Int = 2641
    private var mainComicId: Int = 1
    private var compositeDisposable = CompositeDisposable()

    private fun handleError(throwable: Throwable) {
        Log.println(Log.ERROR, "error", throwable.toString())
        errorLiveData.postValue(throwable.toString())
    }

    fun onViewDestroy() {
        compositeDisposable.clear()
    }
}
