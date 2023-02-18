package com.example.nytnews.di

import com.example.nytnews.presentation.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(homeViewModel: HomeViewModel)
}