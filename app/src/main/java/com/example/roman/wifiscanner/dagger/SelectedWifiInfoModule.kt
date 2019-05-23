package com.example.roman.wifiscanner.dagger

import com.example.roman.wifiscanner.wifi.wifidataclass.ISelectedWifiInfo
import com.example.roman.wifiscanner.wifi.wifidataclass.SelectedWifiInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SelectedWifiInfoModule {
    @Provides
    @Singleton
    fun provideSelectedWifiInfo(): ISelectedWifiInfo = SelectedWifiInfo()
}