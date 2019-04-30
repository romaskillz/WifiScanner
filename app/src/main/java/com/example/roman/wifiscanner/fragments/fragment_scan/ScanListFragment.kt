package com.example.roman.wifiscanner.fragments.fragment_scan

import com.hannesdorfmann.mosby3.mvp.MvpFragment

class ScanListFragment : MvpFragment<IScanView, ScanListPresenter>(), IScanView {

    override fun createPresenter(): ScanListPresenter {
        TODO("not implemented: need to create presenter")
    }

    fun startScan() {

    }

    fun onItemClick() {
        //TODO("not implemented") need to handle onItem click
    }
}
