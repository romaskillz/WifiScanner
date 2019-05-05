package com.example.roman.wifiscanner

import android.app.Application
import com.example.roman.wifiscanner.dagger.AppComponent
import com.example.roman.wifiscanner.dagger.AppModule
import com.example.roman.wifiscanner.dagger.DaggerAppComponent


class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: App): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}