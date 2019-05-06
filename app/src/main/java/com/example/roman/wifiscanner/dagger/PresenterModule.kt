package com.example.roman.wifiscanner.dagger

import com.example.roman.wifiscanner.fragments.fragment_scan.IScanView
import com.example.roman.wifiscanner.presenters.ScanListPresenter
import com.example.roman.wifiscanner.wifi.IWifiScanner
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
    @Provides
    @Singleton
    fun provideScanListPresenter(mScannerService: IWifiScanner):
            MvpBasePresenter<IScanView> = ScanListPresenter(mScannerService)
}