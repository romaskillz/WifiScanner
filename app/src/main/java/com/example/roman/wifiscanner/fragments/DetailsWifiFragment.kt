package com.example.roman.wifiscanner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.roman.wifiscanner.App
import com.example.roman.wifiscanner.R
import com.example.roman.wifiscanner.interfaces.IDetailsView
import com.example.roman.wifiscanner.presenters.DetailsPresenter
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import javax.inject.Inject

class DetailsWifiFragment : MvpFragment<IDetailsView, DetailsPresenter>(), IDetailsView {

    @Inject
    lateinit var mPresenter: DetailsPresenter

    override fun createPresenter(): DetailsPresenter {
        (activity!!.application as App).appComponent.inject(this)
        return mPresenter
    }

    companion object {
        fun newInstance(data: Bundle): DetailsWifiFragment {
            val fragment = DetailsWifiFragment()
            fragment.arguments = data
            return fragment
        }
    }

    lateinit var ssidText: TextView
    lateinit var islockedText: TextView
    lateinit var mignalLevelText: TextView
    lateinit var connectButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_details_wifi, container, false)
        ssidText = rootView.findViewById(R.id.wifi_name)
        islockedText = rootView.findViewById(R.id.wifi_security)
        mignalLevelText = rootView.findViewById(R.id.wifi_signal)
        connectButton = rootView.findViewById(R.id.b_connect)

        ssidText.text = arguments?.get("ssid").toString()
        islockedText.text = arguments?.get("isLocked").toString()
        mignalLevelText.text = arguments?.get("signalLevel").toString()
        connectButton.setOnClickListener {
            presenter.connectToSsid()
        }

        return rootView
    }

    override fun connect() {
        Toast.makeText(activity, "Connected", Toast.LENGTH_SHORT).show()
    }


}
