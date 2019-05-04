package com.example.roman.wifiscanner.fragments.fragment_scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.roman.wifiscanner.App
import com.example.roman.wifiscanner.R
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import kotlinx.android.synthetic.main.fragment_scan_list.view.*
import javax.inject.Inject

class ScanListFragment : MvpFragment<IScanView, ScanListPresenter>(), IScanView {

    @Inject
    lateinit var mPresenter: ScanListPresenter

    override fun createPresenter(): ScanListPresenter {
        App.mComponent.inject(this)
        return mPresenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_scan_list, container, false)
        rootView.bShow.setOnClickListener { mPresenter.startScan() }
        return rootView
    }

    override fun startScan() {
        Toast.makeText(activity, "PUSHED", Toast.LENGTH_SHORT).show()
    }

    fun onItemClick() {
        //TODO("not implemented") need to handle onItem click
    }
}
