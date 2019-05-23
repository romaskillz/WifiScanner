package com.example.roman.wifiscanner.dagger

import android.content.Context
import com.example.roman.wifiscanner.interfaces.IDetailsFragmentView
import com.example.roman.wifiscanner.interfaces.IWifiScanner
import com.example.roman.wifiscanner.presenters.DetailsPresenter
import com.example.roman.wifiscanner.wifi.wifidataclass.ISelectedWifiInfo
import com.example.roman.wifiscanner.wifi.wifidataclass.SelectedWifiInfo
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DetailsPresenterModule {
    @Provides
    @Singleton
    fun provideDetailsPresenter(mScannerService: IWifiScanner, mContext: Context, selectedWifi: SelectedWifiInfo): MvpBasePresenter<IDetailsFragmentView> =
        DetailsPresenter(mScannerService, mContext, selectedWifi)
}