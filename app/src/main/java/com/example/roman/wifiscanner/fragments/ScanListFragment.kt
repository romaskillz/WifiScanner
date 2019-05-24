package com.example.roman.wifiscanner.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.example.roman.wifiscanner.App
import com.example.roman.wifiscanner.R
import com.example.roman.wifiscanner.adapters.ScanListAdapter
import com.example.roman.wifiscanner.interfaces.IScanListFragmentView
import com.example.roman.wifiscanner.presenters.ScanListPresenter
import com.example.roman.wifiscanner.wifi.wifidataclass.WifiData
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import kotlinx.android.synthetic.main.fragment_scan_list.*
import javax.inject.Inject

class ScanListFragment : MvpFragment<IScanListFragmentView, ScanListPresenter>(), IScanListFragmentView,
    ScanListAdapter.OnItemClickListener {

    @Inject
    lateinit var mPresenter: ScanListPresenter

    override fun createPresenter(): ScanListPresenter {
        (activity!!.application as App).appComponent.inject(this)
        return mPresenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scan_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mButtonShow.setOnClickListener {
            mPresenter.startScan()
        }
    }

    override fun showProgressDialog() {
        mWifiRecycler.visibility = GONE
        mEmptyList.visibility = GONE
        mProgressBar.visibility = VISIBLE
    }

    override fun hideProgressDialog() {
        mProgressBar.visibility = GONE
        mWifiRecycler.visibility = VISIBLE
    }

    override fun setAdapterData(items: List<WifiData>) {
        val mAdapter = ScanListAdapter(items, this)
        mWifiRecycler.layoutManager = LinearLayoutManager(activity)
        mWifiRecycler.adapter = mAdapter
        mAdapter.updateItems(items)
    }

    override fun showNextScreen() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.container, DetailsWifiFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onItemClick(item: WifiData) {
        presenter.nextScreenWifiInfo(item)
    }
}
