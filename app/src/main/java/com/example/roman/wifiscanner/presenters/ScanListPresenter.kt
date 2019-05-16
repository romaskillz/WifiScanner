package com.example.roman.wifiscanner.presenters

import android.util.Log
import com.example.roman.wifiscanner.interfaces.IScanView
import com.example.roman.wifiscanner.interfaces.IWifiScanner
import com.example.roman.wifiscanner.wifi.wifidataclass.WifiData
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import javax.inject.Inject

class ScanListPresenter @Inject internal constructor(private val mScannerService: IWifiScanner) :
    MvpBasePresenter<IScanView>() {

    fun startScan() {
        ifViewAttached { view ->
            view.showProgressDialog()
            mScannerService.scan().subscribe(
                { list ->
                    view.setAdapterData(list)
                    view.hideProgressDialog()
                },
                { throwable ->
                    Log.d("Can't scan: ", throwable.toString())
                })
        }
    }

    fun nextScreenWifiInfo(item: WifiData) {
        ifViewAttached { view ->
            view.showNextScreen(item)
        }
    }
}
