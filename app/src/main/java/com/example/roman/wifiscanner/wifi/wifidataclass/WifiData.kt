package com.example.roman.wifiscanner.wifi.wifidataclass

data class WifiData(private val ssid: String, private val isLocked: Boolean, val signalLevel: Int)