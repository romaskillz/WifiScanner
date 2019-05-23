package com.example.roman.wifiscanner.presenters

import android.content.Context
import com.example.roman.wifiscanner.Constants
import com.example.roman.wifiscanner.Constants.FREQUENCY
import com.example.roman.wifiscanner.R
import com.example.roman.wifiscanner.interfaces.IDetailsFragmentView
import com.example.roman.wifiscanner.interfaces.IWifiScanner
import com.example.roman.wifiscanner.wifi.wifidataclass.ISelectedWifiInfo
import com.example.roman.wifiscanner.wifi.wifistate.WifiNetworkType
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import javax.inject.Inject

class DetailsPresenter @Inject internal constructor(
    private val mScannerService: IWifiScanner,
    private val mContext: Context,
    private val mSelectedWifiInfo: ISelectedWifiInfo
) : MvpBasePresenter<IDetailsFragmentView>() {


    override fun attachView(view: IDetailsFragmentView) {
        super.attachView(view)
        mScannerService.checkWifiStatus().subscribe()

        val security: String
        if (mSelectedWifiInfo.isLocked) {
            security = mContext.getString(R.string.need_password_wifi)
            view.showPassword()
        } else {
            security = mContext.getString(R.string.open_wifi)
            view.hidePassword()
        }

        val frequency =
            if (mSelectedWifiInfo.frequency < FREQUENCY)
                mContext.getString(R.string.low_frequency)
            else
                mContext.getString(R.string.high_frequency)

        val idWifiIcon: Int = when {
            mSelectedWifiInfo.signalLevel >= Constants.BEST_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_best_signal
            mSelectedWifiInfo.signalLevel >= Constants.MIDDLE_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_middle_signal
            mSelectedWifiInfo.signalLevel >= Constants.BAD_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_bad_signal
            else -> R.drawable.ic_wifi_worst_signal
        }

        view.setInfo(mSelectedWifiInfo.nameWifi, security, frequency, idWifiIcon)
    }

    fun connectToSsid(pass: String) {
        ifViewAttached { view ->
            if (mSelectedWifiInfo.isLocked) {
                if (!pass.isBlank()) {
                    mScannerService.connectToSelected(mSelectedWifiInfo.nameWifi, pass)
                } else {
                    view.showEmptyPassword()
                }
            } else {
                mScannerService.connectToSelected(mSelectedWifiInfo.nameWifi, pass, WifiNetworkType.OPEN)
            }
        }
    }
}