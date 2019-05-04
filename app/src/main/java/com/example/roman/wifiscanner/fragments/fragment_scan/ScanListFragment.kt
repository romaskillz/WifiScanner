package com.example.roman.wifiscanner.fragments.fragment_scan

import com.example.roman.wifiscanner.dagger.AppComponent
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import javax.inject.Inject

class ScanListFragment : MvpFragment<IScanView, ScanListPresenter>(), IScanView {

    @Inject
    lateinit var mComponent: AppComponent

    override fun createPresenter(): ScanListPresenter {
        return mComponent.inject(this)
    }

    fun startScan() {

    }

    fun onItemClick() {
        //TODO("not implemented") need to handle onItem click
    }
}
