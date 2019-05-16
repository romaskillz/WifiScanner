package com.example.roman.wifiscanner.dagger

import android.content.Context
import com.example.roman.wifiscanner.interfaces.IWifiScanner
import com.example.roman.wifiscanner.wifi.ScannerService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ScannerModule {
    @Provides
    @Singleton
    fun provideScanListPresenter(mContext: Context): IWifiScanner = ScannerService(mContext)
}