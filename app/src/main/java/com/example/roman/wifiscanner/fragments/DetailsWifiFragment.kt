package com.example.roman.wifiscanner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.roman.wifiscanner.App
import com.example.roman.wifiscanner.Constants
import com.example.roman.wifiscanner.R
import com.example.roman.wifiscanner.interfaces.IDetailsFragmentView
import com.example.roman.wifiscanner.presenters.DetailsPresenter
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import kotlinx.android.synthetic.main.fragment_details_wifi.*
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

    private lateinit var nameWifi: String
    private var isLocked: Boolean = false
    private lateinit var frequency: String
    private lateinit var speed: String
    private var signalLevel: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details_wifi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        mWifiName.text = nameWifi
        mWifiSecurityType.text = if (isLocked) "Open" else "Wi-Fi need password"
        mWifiFrequency.text = "$frequency mHz"

        val idWifiIcon: Int = when {
            signalLevel >= Constants.BEST_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_best_signal
            signalLevel >= Constants.MIDDLE_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_middle_signal
            signalLevel >= Constants.BAD_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_bad_signal
            else -> R.drawable.ic_wifi_worst_signal
        }
        mWifiSignalImg.setImageResource(idWifiIcon)

        mWifiConnect.setOnClickListener {
            mWifiPasswordEt.text.clear()
            presenter.connectToSsid(nameWifi, mWifiPasswordEt.text.toString(), isLocked)
        }
    }

    private fun getData() {
        nameWifi = arguments?.get("ssid") as String
        isLocked = arguments?.get("isLocked") as Boolean
        frequency = arguments?.get("frequency").toString()
        speed = arguments?.get("speed").toString()
        signalLevel = arguments?.get("signalLevel").toString().toInt()
    }

    override fun showEmptyPassword() {
        Toast.makeText(activity, "Password is empty", Toast.LENGTH_SHORT).show()
    }
}
