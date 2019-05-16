package com.example.roman.wifiscanner.dagger

import com.example.roman.wifiscanner.interfaces.IScanView
import com.example.roman.wifiscanner.presenters.ScanListPresenter
import com.example.roman.wifiscanner.interfaces.IWifiScanner
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ScanListPresenterModule {
    @Provides
    @Singleton
    fun provideScanListPresenter(mScannerService: IWifiScanner): MvpBasePresenter<IScanView> =
        ScanListPresenter(mScannerService)
}