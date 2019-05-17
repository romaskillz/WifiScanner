package com.example.roman.wifiscanner.dagger

import com.example.roman.wifiscanner.interfaces.IDetailsFragmentView
import com.example.roman.wifiscanner.interfaces.IWifiScanner
import com.example.roman.wifiscanner.presenters.DetailsPresenter
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DetailsPresenterModule {
    @Provides
    @Singleton
    fun provideDetailsPresenter(mScannerService: IWifiScanner): MvpBasePresenter<IDetailsFragmentView> =
        DetailsPresenter(mScannerService)
}