package com.example.roman.wifiscanner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.roman.wifiscanner.App
import com.example.roman.wifiscanner.R
import com.example.roman.wifiscanner.interfaces.IDetailsFragmentView
import com.example.roman.wifiscanner.presenters.DetailsPresenter
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import javax.inject.Inject

class DetailsWifiFragment : MvpFragment<IDetailsFragmentView, DetailsPresenter>(), IDetailsFragmentView {

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
    lateinit var mInputPassword: EditText
    lateinit var mConnectButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_details_wifi, container, false)

        ssidText = rootView.findViewById(R.id.wifi_name)
        islockedText = rootView.findViewById(R.id.wifi_security)
        mignalLevelText = rootView.findViewById(R.id.wifi_signal)
        mInputPassword = rootView.findViewById(R.id.et_pass)
        mConnectButton = rootView.findViewById(R.id.b_connect)

        val nameWifi = arguments?.get("ssid") as String
        val isLocked = arguments?.get("isLocked") as Boolean

        ssidText.text = nameWifi
        islockedText.text = if (isLocked) "Open" else "Wi-Fi need password"
        mignalLevelText.text = arguments?.get("signalLevel").toString()

        mConnectButton.setOnClickListener {
            presenter.connectToSsid(nameWifi, mInputPassword.text.toString(), isLocked)
        }

        return rootView
    }

    override fun showEmptyPassword() {
        Toast.makeText(activity, "Password is empty", Toast.LENGTH_SHORT).show()
    }
}
