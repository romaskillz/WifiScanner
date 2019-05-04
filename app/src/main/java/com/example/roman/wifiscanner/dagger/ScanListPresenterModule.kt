package com.example.roman.wifiscanner.dagger

import com.example.roman.wifiscanner.fragments.fragment_scan.ScanListPresenter
import dagger.Module
import dagger.Provides

@Module
class ScanListPresenterModule{

    @Provides
    fun provideScanListPresenter(): ScanListPresenter{
        return ScanListPresenter()
    }
}
