package com.example.roman.wifiscanner.presenters

import android.util.Log
import com.example.roman.wifiscanner.interfaces.IScanListFragmentView
import com.example.roman.wifiscanner.interfaces.IWifiScanner
import com.example.roman.wifiscanner.wifi.wifidataclass.ISelectedWifiInfo
import com.example.roman.wifiscanner.wifi.wifidataclass.WifiData
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import javax.inject.Inject

class ScanListPresenter @Inject internal constructor(private val mScannerService: IWifiScanner,
                                                     private val mSelectedWifiInfo: ISelectedWifiInfo) :
    MvpBasePresenter<IScanListFragmentView>() {

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
            mSelectedWifiInfo.nameWifi = item.ssid
            mSelectedWifiInfo.isLocked = item.isLocked
            mSelectedWifiInfo.frequency = item.frequency
            mSelectedWifiInfo.signalLevel = item.signalLevel
            view.showNextScreen()
        }
    }
}