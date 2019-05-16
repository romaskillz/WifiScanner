package com.example.roman.wifiscanner.dagger

import android.support.v7.widget.RecyclerView
import com.example.roman.wifiscanner.adapters.ScanListAdapter
import com.example.roman.wifiscanner.wifi.wifidataclass.WifiData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AdapterModule {
    @Provides
    @Singleton
    fun provideScanListAdapter(wifiItems: List<WifiData>, itemClickListener: ScanListAdapter.OnItemClickListener):
            RecyclerView.Adapter<ScanListAdapter.WifiItemViewHolder> =
        ScanListAdapter(wifiItems, itemClickListener)
}