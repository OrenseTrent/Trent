package com.example.trent.di

import com.example.trent.model.DummyService
import com.example.trent.viewmodel.ListViewModel
import dagger.Component


@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: DummyService)

    fun inject(viewModel: ListViewModel)
}