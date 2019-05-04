package com.example.roman.wifiscanner.dagger

import com.example.roman.wifiscanner.fragments.fragment_scan.ScanListFragment
import com.example.roman.wifiscanner.wifi.ScannerService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ScanListModule::class])
interface AppComponent {
    fun inject(scanListFragment: ScanListFragment)
    fun inject(scanListFragment: ScannerService)
}