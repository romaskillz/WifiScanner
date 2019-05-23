package com.example.roman.wifiscanner.wifi.wifidataclass

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectedWifiInfo @Inject constructor() : ISelectedWifiInfo {
    override var nameWifi: String = ""
    override var isLocked: Boolean = false
    override var frequency: Int = 0
    override var signalLevel: Int = 0
}