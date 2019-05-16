package com.example.roman.wifiscanner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.roman.wifiscanner.fragments.ScanListFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, ScanListFragment())
        fragmentTransaction.commit()
    }
}