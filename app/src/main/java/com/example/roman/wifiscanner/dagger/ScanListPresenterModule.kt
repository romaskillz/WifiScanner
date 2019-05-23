package com.example.roman.wifiscanner.dagger

import com.example.roman.wifiscanner.interfaces.IScanListFragmentView
import com.example.roman.wifiscanner.interfaces.IWifiScanner
import com.example.roman.wifiscanner.presenters.ScanListPresenter
import com.example.roman.wifiscanner.wifi.wifidataclass.ISelectedWifiInfo
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ScanListPresenterModule {
    @Provides
    @Singleton
    fun provideScanListPresenter(
        mScannerService: IWifiScanner,
        mSelectedWifiInfo: ISelectedWifiInfo
    ): MvpBasePresenter<IScanListFragmentView> =
        ScanListPresenter(mScannerService, mSelectedWifiInfo)
}