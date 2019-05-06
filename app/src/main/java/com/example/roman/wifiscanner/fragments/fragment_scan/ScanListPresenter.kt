package com.example.roman.wifiscanner.fragments.fragment_scan

import com.example.roman.wifiscanner.wifi.IWifiScanner
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import javax.inject.Inject

class ScanListPresenter @Inject internal constructor(private val mScannerService: IWifiScanner) :
    MvpBasePresenter<IScanView>() {

    fun startScan() {
        ifViewAttached { view ->
            view.startScan()
            mScannerService.scan().subscribe()
//            mScannerService.scanWithoutRx()
        }
    }
}
