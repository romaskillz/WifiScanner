package com.example.roman.wifiscanner.wifi

import com.example.roman.wifiscanner.wifi.wifidataclass.WifiData
import com.example.roman.wifiscanner.wifi.wifistate.WifiNetworkType
import com.example.roman.wifiscanner.wifi.wifistate.WifiStateEvent
import io.reactivex.Observable
import io.reactivex.Single

interface IWifiScanner {
    fun scan(): Single<List<WifiData>>
    fun observeWifiOnOffStatus(): Observable<WifiStateEvent>
    fun observeWifiConnectionStatus(): Observable<WifiStateEvent>
    fun checkWifiStatus(): Observable<WifiStateEvent>
    fun getCurrentSsid(): String
    fun connectToSelected(ssid: String, pass: String, type: WifiNetworkType = WifiNetworkType.SECURED)
}