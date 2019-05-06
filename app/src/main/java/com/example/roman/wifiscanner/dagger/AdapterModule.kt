package com.example.roman.wifiscanner.dagger

import android.support.v7.widget.RecyclerView
import com.example.roman.wifiscanner.fragments.fragment_scan.ScanListAdapter
import com.example.roman.wifiscanner.wifi.wifidataclass.WifiData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AdapterModule {
    @Provides
    @Singleton
    fun provideScanListAdapter(wifiItems: List<WifiData>):
            RecyclerView.Adapter<ScanListAdapter.WifiItemViewHolder> = ScanListAdapter(wifiItems)
}