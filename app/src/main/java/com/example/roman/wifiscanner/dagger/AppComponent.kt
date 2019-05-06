package com.example.roman.wifiscanner.dagger

import com.example.roman.wifiscanner.fragments.fragment_scan.ScanListFragment
import com.example.roman.wifiscanner.presenters.ScanListPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        PresenterModule::class,
        ScannerModule::class,
        AdapterModule::class
    ]
)
interface AppComponent {
    fun inject(target: ScanListFragment)
    fun inject(target: ScanListPresenter)
}