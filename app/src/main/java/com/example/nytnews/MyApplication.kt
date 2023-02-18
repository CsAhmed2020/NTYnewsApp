package com.example.nytnews

import android.app.Application
import com.example.nytnews.di.AppComponent

import com.example.nytnews.di.DaggerAppComponent

class MyApplication : Application() {

    private lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        //appComponent = DaggerAppComponent.builder().appModule(AppModule()).build()
       appComponent = DaggerAppComponent.builder().build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}