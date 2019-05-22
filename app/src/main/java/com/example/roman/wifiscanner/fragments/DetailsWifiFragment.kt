package com.example.roman.wifiscanner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.roman.wifiscanner.App
import com.example.roman.wifiscanner.Constants
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

    lateinit var mSsidText: TextView
    lateinit var mIsLockedText: TextView
    lateinit var mSignalLevelImage: ImageView
    lateinit var mInputPassword: EditText
    lateinit var mConnectButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_details_wifi, container, false)

        mSsidText = rootView.findViewById(R.id.wifi_name)
        mIsLockedText = rootView.findViewById(R.id.wifi_security)
        mSignalLevelImage = rootView.findViewById(R.id.wifi_signal)
        mInputPassword = rootView.findViewById(R.id.et_pass)
        mConnectButton = rootView.findViewById(R.id.b_connect)

        val nameWifi = arguments?.get("ssid") as String
        val isLocked = arguments?.get("isLocked") as Boolean

        mSsidText.text = nameWifi
        mIsLockedText.text = if (isLocked) "Open" else "Wi-Fi need password"
        val signalLevel = arguments?.get("signalLevel").toString().toInt()

        val idWifiIcon: Int = when {
            //  Wi-Fi signal values got from:
            //  https://www.metageek.com/training/resources/wifi-signal-strength-basics.html
            signalLevel >= Constants.BEST_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_best_signal
            signalLevel >= Constants.MIDDLE_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_middle_signal
            signalLevel >= Constants.BAD_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_bad_signal
            else -> R.drawable.ic_wifi_worst_signal
        }
        mSignalLevelImage.setImageResource(idWifiIcon)

        mConnectButton.setOnClickListener {
            mInputPassword.text.clear()
            presenter.connectToSsid(nameWifi, mInputPassword.text.toString(), isLocked)
        }
        return rootView
    }

    override fun showEmptyPassword() {
        Toast.makeText(activity, "Password is empty", Toast.LENGTH_SHORT).show()
    }
}
