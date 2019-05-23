package com.example.roman.wifiscanner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.example.roman.wifiscanner.App
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details_wifi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mWifiConnect.setOnClickListener {
            presenter.connectToSsid(mWifiPasswordEt.text.toString())
            mWifiPasswordEt.text.clear()
        }
    }

    override fun hidePassword() {
        mWifiPasswordEt.visibility = GONE
        tipText.visibility = GONE
        tipTextSecond.visibility = VISIBLE
    }

    override fun showPassword() {
        mWifiPasswordEt.visibility = VISIBLE
        tipText.visibility = VISIBLE
        tipTextSecond.visibility = GONE
    }

    override fun showEmptyPassword() {
        Toast.makeText(activity, "Password is empty", Toast.LENGTH_SHORT).show()
    }

    override fun setInfo(name: String, security: String, frequency: String, signal: Int) {
        mWifiName.text = name
        mWifiSecurityType.text = security
        mWifiFrequency.text = frequency
        mWifiSignalImg.setImageResource(signal)
    }
}
