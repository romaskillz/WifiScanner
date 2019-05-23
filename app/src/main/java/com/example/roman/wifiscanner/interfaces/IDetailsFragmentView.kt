package com.example.roman.wifiscanner.interfaces

import com.hannesdorfmann.mosby3.mvp.MvpView

interface IDetailsFragmentView : MvpView {
    fun showEmptyPassword()
    fun hidePassword()
    fun showPassword()
    fun setInfo(name: String, security: String, frequency: String, signal: Int)
}