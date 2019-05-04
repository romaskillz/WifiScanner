package com.example.roman.wifiscanner.dagger

import com.example.roman.wifiscanner.App
import com.example.roman.wifiscanner.fragments.fragment_scan.ScanListFragment
import dagger.Component

@Component(modules = [ScanListPresenterModule::class])
interface AppComponent {
    fun inject(scanListPresenterComponent: ScanListFragment)
}