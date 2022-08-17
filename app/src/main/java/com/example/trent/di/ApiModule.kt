package com.example.trent.di

import com.example.trent.model.DummyApi
import com.example.trent.model.DummyService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class ApiModule {


    private val BASE_URL = "https://dummyjson.com/"

    @Provides
    fun provideDummyApi(): DummyApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(DummyApi::class.java)
    }

    @Provides
    fun provideCountriesService(): DummyService {
        return DummyService()
    }
}