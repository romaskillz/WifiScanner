package com.example.roman.wifiscanner.fragments.fragment_deatails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roman.wifiscanner.App
import com.example.roman.wifiscanner.R
import com.example.roman.wifiscanner.presenters.DetailsPresenter
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import javax.inject.Inject

class DetailsWifiFragment : /*MvpFragment<IDetailsView, DetailsPresenter>(), IDetailsView*/ Fragment() {

    /*@Inject
    lateinit var mPresenter: DetailsPresenter

    override fun createPresenter(): DetailsPresenter {
        (activity!!.application as App).appComponent.inject(this)
        return mPresenter
    }
*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }
}