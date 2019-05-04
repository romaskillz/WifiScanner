package com.example.roman.wifiscanner

import android.app.Application
import com.example.roman.wifiscanner.dagger.AppComponent
import com.example.roman.wifiscanner.dagger.DaggerAppComponent
import javax.inject.Inject

class App : Application() {

    @Inject
    private lateinit var mComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        mComponent = DaggerAppComponent.create()
    }

    fun getComponent(): AppComponent {
        return mComponent
    }
}