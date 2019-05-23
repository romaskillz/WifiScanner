package com.example.roman.wifiscanner.wifi.wifidataclass

interface ISelectedWifiInfo {
    var nameWifi: String
    var isLocked: Boolean
    var frequency: Int
    var signalLevel: Int
}