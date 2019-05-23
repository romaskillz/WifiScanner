package com.example.roman.wifiscanner.dagger

import com.example.roman.wifiscanner.fragments.DetailsWifiFragment
import com.example.roman.wifiscanner.fragments.ScanListFragment
import com.example.roman.wifiscanner.presenters.DetailsPresenter
import com.example.roman.wifiscanner.presenters.ScanListPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ScanListPresenterModule::class,
        DetailsPresenterModule::class,
        ScannerModule::class,
        AdapterModule::class,
        SelectedWifiInfoModule::class
    ]
)
interface AppComponent {
    fun inject(target: ScanListFragment)
    fun inject(target: DetailsWifiFragment)
    fun inject(target: ScanListPresenter)
    fun inject(target: DetailsPresenter)
}