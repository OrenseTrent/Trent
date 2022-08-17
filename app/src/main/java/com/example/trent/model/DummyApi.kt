package com.example.trent.model

import io.reactivex.Single
import retrofit2.http.GET

interface DummyApi {

    @GET("/products")
    fun getDummyList(): Single<Dummy>
}