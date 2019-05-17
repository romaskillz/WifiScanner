package com.example.roman.wifiscanner.presenters

import com.example.roman.wifiscanner.interfaces.IDetailsFragmentView
import com.example.roman.wifiscanner.interfaces.IWifiScanner
import com.example.roman.wifiscanner.wifi.wifistate.WifiNetworkType
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import javax.inject.Inject

class DetailsPresenter @Inject internal constructor(private val mScannerService: IWifiScanner) :
    MvpBasePresenter<IDetailsFragmentView>() {


    override fun attachView(view: IDetailsFragmentView) {
        super.attachView(view)
        mScannerService.checkWifiStatus().subscribe()
    }

    fun connectToSsid(ssid: String, pass: String, isSecured: Boolean) {
        ifViewAttached { view ->
            if(isSecured) {
                if (!pass.isBlank()) {
                    mScannerService.connectToSelected(ssid, pass)
                } else {
                    view.showEmptyPassword()
                }
            } else{
                mScannerService.connectToSelected(ssid, pass, WifiNetworkType.OPEN)
            }
        }
    }
}