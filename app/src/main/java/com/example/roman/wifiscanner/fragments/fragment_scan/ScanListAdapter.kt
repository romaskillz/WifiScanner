package com.example.roman.wifiscanner.fragments.fragment_scan

import android.content.ClipData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.roman.wifiscanner.R
import com.example.roman.wifiscanner.wifi.Constants.BAD_WIFI_SIGNAL_VALUE
import com.example.roman.wifiscanner.wifi.Constants.BEST_WIFI_SIGNAL_VALUE
import com.example.roman.wifiscanner.wifi.Constants.MIDDLE_WIFI_SIGNAL_VALUE
import com.example.roman.wifiscanner.wifi.wifidataclass.WifiData
import javax.inject.Inject

class ScanListAdapter @Inject internal constructor(private var wifiItems: List<WifiData>) :
    RecyclerView.Adapter<ScanListAdapter.WifiItemViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WifiItemViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.wifi_item, p0, false)
        return WifiItemViewHolder(v)
    }

    override fun getItemCount(): Int {
        return wifiItems.size
    }

    override fun onBindViewHolder(viewHolder: WifiItemViewHolder, i: Int) {
        val wifi = wifiItems[i]
        viewHolder.mWifiTitle.text = wifi.ssid
        viewHolder.mWifiSecurityIcon.visibility = if (wifi.isLocked) View.VISIBLE else View.GONE
        val idWifiIcon: Int = when {
            //  Wi-Fi signal values got from:
            //  https://www.metageek.com/training/resources/wifi-signal-strength-basics.html
            wifi.signalLevel >= BEST_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_best_signal
            wifi.signalLevel >= MIDDLE_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_middle_signal
            wifi.signalLevel >= BAD_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_bad_signal
            else -> R.drawable.ic_wifi_worst_signal
        }
        viewHolder.mWifiSignalStrength.setImageResource(idWifiIcon)
    }

    class WifiItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mWifiTitle: TextView = itemView.findViewById(R.id.wifi_title) as TextView
        var mWifiSignalStrength: ImageView = itemView.findViewById(R.id.wifi_signal) as ImageView
        var mWifiSecurityIcon: ImageView = itemView.findViewById(R.id.wifi_security) as ImageView

    }

    fun updateItems(items: List<WifiData>) {
        wifiItems = items
        notifyDataSetChanged()
    }
}