package com.example.roman.wifiscanner.fragment_scan

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class ScanListPresenter : MvpBasePresenter<IScanView>() {
    override fun attachView(view: IScanView) {
        super.attachView(view)
        ifViewAttached { view ->
        }
    }
}
