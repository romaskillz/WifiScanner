package com.example.roman.wifiscanner.dagger

import com.example.roman.wifiscanner.fragments.fragment_scan.ScanListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ScanListModule{

    @Provides
    @Singleton
    fun provideScanListPresenter(): ScanListPresenter{
        return ScanListPresenter()
    }
}
