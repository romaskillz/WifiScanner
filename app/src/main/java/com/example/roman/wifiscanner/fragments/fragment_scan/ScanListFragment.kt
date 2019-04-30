package com.example.roman.wifiscanner.fragments.fragment_scan

import com.example.roman.wifiscanner.dagger.ScannerServiceComponent
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import javax.inject.Inject

class ScanListFragment : MvpFragment<IScanView, ScanListPresenter>(), IScanView {

    @Inject
    lateinit var mComponent: ScannerServiceComponent

    override fun createPresenter(): ScanListPresenter {
        return /*mComponent.presenter()*/ScanListPresenter()

    }

    fun startScan() {

    }

    fun onItemClick() {
        //TODO("not implemented") need to handle onItem click
    }
}
