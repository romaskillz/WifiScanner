package com.example.roman.wifiscanner

import android.app.Application
import com.example.roman.wifiscanner.dagger.AppComponent
import com.example.roman.wifiscanner.dagger.DaggerAppComponent
import javax.inject.Singleton


class App : Application() {
    companion object {
        var mComponent: AppComponent = DaggerAppComponent.create()
    }
}