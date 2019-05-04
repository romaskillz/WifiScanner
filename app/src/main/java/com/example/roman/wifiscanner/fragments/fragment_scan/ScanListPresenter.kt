package com.example.roman.wifiscanner.fragments.fragment_scan

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class ScanListPresenter : MvpBasePresenter<IScanView>() {

    fun startScan() {
        ifViewAttached { view ->
            view.startScan()
        }
    }
}
