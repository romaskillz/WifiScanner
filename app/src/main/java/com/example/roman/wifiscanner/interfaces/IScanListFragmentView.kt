package com.example.roman.wifiscanner.interfaces

import com.example.roman.wifiscanner.wifi.wifidataclass.WifiData
import com.hannesdorfmann.mosby3.mvp.MvpView

interface IScanListFragmentView : MvpView {
    fun showProgressDialog()
    fun hideProgressDialog()
    fun setAdapterData(items: List<WifiData>)
    fun showNextScreen()
}