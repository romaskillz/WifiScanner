package com.example.roman.wifiscanner.presenters

import android.util.Log
import com.example.roman.wifiscanner.fragments.fragment_scan.IScanView
import com.example.roman.wifiscanner.wifi.IWifiScanner
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import javax.inject.Inject

class ScanListPresenter @Inject internal constructor(private val mScannerService: IWifiScanner) :
    MvpBasePresenter<IScanView>() {

    fun startScan() {
        ifViewAttached { view ->
            view.startScan()
            mScannerService.scan().subscribe(
                { list ->
                    view.setAdapterData(list)
                },
                { throwable ->
                    Log.d("Can't scan: ", throwable.toString())
                })
        }
    }
}
