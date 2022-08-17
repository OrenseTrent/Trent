package com.example.trent.model

import com.example.trent.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class DummyService {

    @Inject
    lateinit var api: DummyApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getDummy(): Single<Dummy> {
        return api.getDummyList()
    }
}