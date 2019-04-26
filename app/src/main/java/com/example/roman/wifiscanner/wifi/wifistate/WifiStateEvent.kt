package com.example.roman.wifiscanner.wifi.wifistate

data class WifiStateEvent(
    val state: WifiState = WifiState.UNKNOWN,
    val ssid: String = ""
)