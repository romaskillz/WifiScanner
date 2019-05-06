package com.example.roman.wifiscanner.fragments.fragment_scan

import com.example.roman.wifiscanner.wifi.wifidataclass.WifiData
import com.hannesdorfmann.mosby3.mvp.MvpView

interface IScanView: MvpView {
    fun showProgressDialog()
    fun hideProgressDialog()
    fun setAdapterData(items: List<WifiData>)
}