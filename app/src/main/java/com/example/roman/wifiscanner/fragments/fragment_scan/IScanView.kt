package com.example.roman.wifiscanner.fragments.fragment_scan

import com.example.roman.wifiscanner.wifi.wifidataclass.WifiData
import com.hannesdorfmann.mosby3.mvp.MvpView

interface IScanView: MvpView {
    fun startScan()
    fun setAdapterData(items: List<WifiData>)
}