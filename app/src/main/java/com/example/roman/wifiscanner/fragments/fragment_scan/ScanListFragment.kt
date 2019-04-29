package com.example.roman.wifiscanner.fragments.fragment_scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roman.wifiscanner.R
import com.hannesdorfmann.mosby3.mvp.MvpFragment

class ScanListFragment : MvpFragment<IScanView, ScanListPresenter>(),
    IScanView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scan_list, container, false)
    }

    override fun createPresenter(): ScanListPresenter {
        TODO("not implemented: need to create presenter")
    }

    fun startScan() {

    }

    fun onItemClick() {
        //TODO("not implemented") need to handle onItem click
    }
}
