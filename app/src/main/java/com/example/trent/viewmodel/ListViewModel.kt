package com.example.trent.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trent.di.DaggerApiComponent
import com.example.trent.model.Dummy
import com.example.trent.model.DummyService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel: ViewModel() {

    @Inject
    lateinit var dummyService: DummyService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val dummy = MutableLiveData<Dummy>()
    val dummyLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchDummy()
    }

    private fun fetchDummy() {
        loading.value = true
        disposable.add(
            dummyService.getDummy()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Dummy>() {
                    override fun onSuccess(value: Dummy?) {
                        dummy.value = value
                        dummyLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        dummyLoadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}