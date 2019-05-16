package com.example.roman.wifiscanner.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.example.roman.wifiscanner.App
import com.example.roman.wifiscanner.R
import com.example.roman.wifiscanner.adapters.ScanListAdapter
import com.example.roman.wifiscanner.interfaces.IScanListFragmentView
import com.example.roman.wifiscanner.presenters.ScanListPresenter
import com.example.roman.wifiscanner.wifi.wifidataclass.WifiData
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import kotlinx.android.synthetic.main.fragment_scan_list.view.*
import javax.inject.Inject


class ScanListFragment : MvpFragment<IScanListFragmentView, ScanListPresenter>(), IScanListFragmentView, ScanListAdapter.OnItemClickListener {

    @Inject
    lateinit var mPresenter: ScanListPresenter

    private lateinit var mEmptyListText: TextView

    private lateinit var mProgressBar: ProgressBar

    private lateinit var mRecycler: RecyclerView

    override fun createPresenter(): ScanListPresenter {
        (activity!!.application as App).appComponent.inject(this)
        return mPresenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_scan_list, container, false)
        init(rootView)
        return rootView
    }

    private fun init(rootView: View) {
        mEmptyListText = rootView.findViewById(R.id.emptyList) as TextView
        mRecycler = rootView.findViewById(R.id.wifiRecycler) as RecyclerView
        mProgressBar = rootView.findViewById(R.id.progressBar) as ProgressBar
        mRecycler.layoutManager = LinearLayoutManager(activity)
        rootView.bShow.setOnClickListener {
            mPresenter.startScan()
        }
    }

    override fun showProgressDialog() {
        mEmptyListText.visibility = GONE
        mProgressBar.visibility = VISIBLE
    }

    override fun hideProgressDialog() {
        mProgressBar.visibility = GONE
        mRecycler.visibility = VISIBLE
    }

    override fun setAdapterData(items: List<WifiData>) {
        val mAdapter = ScanListAdapter(items, this)
        mRecycler.adapter = mAdapter
        mAdapter.updateItems(items)
    }

    override fun showNextScreen(item: WifiData) {
        val wifiData = Bundle()
        wifiData.putString("ssid", item.ssid)
        wifiData.putBoolean("isLocked", item.isLocked)
        wifiData.putInt("signalLevel", item.signalLevel)
        val detailsFragment = DetailsWifiFragment.newInstance(wifiData)
        
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.container, detailsFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onItemClick(item: WifiData) {
        presenter.nextScreenWifiInfo(item)
    }
}
