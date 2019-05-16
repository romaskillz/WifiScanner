package com.example.roman.wifiscanner.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.roman.wifiscanner.R

class DetailsWifiFragment : Fragment() {

    companion object {
        fun newInstance(data: Bundle): DetailsWifiFragment {
            val fragment = DetailsWifiFragment()
            fragment.arguments = data
            return fragment
        }
    }

    lateinit var mSsid: TextView
    lateinit var mIslocked: TextView
    lateinit var mSignalLevel: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_details_wifi, container, false)
        mSsid = rootView.findViewById(R.id.wifi_name)
        mIslocked = rootView.findViewById(R.id.wifi_security)
        mSignalLevel = rootView.findViewById(R.id.wifi_signal)

        mSsid.text = arguments?.get("ssid").toString()
        mIslocked.text = arguments?.get("isLocked").toString()
        mSignalLevel.text = arguments?.get("signalLevel").toString()

        return rootView
    }
}
