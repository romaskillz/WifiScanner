package com.example.roman.wifiscanner.presenters

import com.example.roman.wifiscanner.Constants.EMPTY_STRING
import com.example.roman.wifiscanner.interfaces.IDetailsFragmentView
import com.example.roman.wifiscanner.interfaces.IWifiScanner
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import javax.inject.Inject

class DetailsPresenter @Inject internal constructor(private val mScannerService: IWifiScanner) :
    MvpBasePresenter<IDetailsFragmentView>() {


    override fun attachView(view: IDetailsFragmentView) {
        super.attachView(view)
        mScannerService.checkWifiStatus().subscribe()
    }

    fun connectToSsid(ssid: String, pass: String) {
        ifViewAttached { view ->
            if (pass == EMPTY_STRING) {
                view.showEmptyPassword()
            } else {
                mScannerService.connectToSelected(ssid, pass)
            }
        }
    }
}
