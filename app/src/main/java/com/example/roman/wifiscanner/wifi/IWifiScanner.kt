package com.example.roman.wifiscanner.wifi

import com.example.roman.wifiscanner.wifi.wifidataclass.WifiData
import com.example.roman.wifiscanner.wifi.wifistate.WifiStateEvent
import io.reactivex.Observable
import io.reactivex.Single

interface IWifiScanner {
    fun scan(): Single<List<WifiData>>
    fun observeWiFiOnOffStatus(): Observable<WifiStateEvent>
    fun observeWiFiConnectionStatus(): Observable<WifiStateEvent>
    fun checkWifiStatus(): Observable<WifiStateEvent>
}