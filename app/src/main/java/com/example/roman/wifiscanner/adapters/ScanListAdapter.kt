package com.example.roman.wifiscanner.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.roman.wifiscanner.Constants.BAD_WIFI_SIGNAL_VALUE
import com.example.roman.wifiscanner.Constants.BEST_WIFI_SIGNAL_VALUE
import com.example.roman.wifiscanner.Constants.FREQUENCY
import com.example.roman.wifiscanner.Constants.MIDDLE_WIFI_SIGNAL_VALUE
import com.example.roman.wifiscanner.R
import com.example.roman.wifiscanner.wifi.wifidataclass.WifiData
import javax.inject.Inject


class ScanListAdapter @Inject internal constructor(
    private var mWifiItems: List<WifiData>,
    private var mOnItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ScanListAdapter.WifiItemViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: WifiData)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WifiItemViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.wifi_item, p0, false)
        return WifiItemViewHolder(v)
    }

    override fun getItemCount() = mWifiItems.size

    override fun onBindViewHolder(viewHolder: WifiItemViewHolder, position: Int) {
        viewHolder.bind(mWifiItems[position], mOnItemClickListener)

    }

    class WifiItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var mWifiTitle: TextView = itemView.findViewById(R.id.mWifiName) as TextView
        private var mWifiFrequency: TextView = itemView.findViewById(R.id.mWifiFrequency) as TextView
        private var mWifiSignalStrength: ImageView = itemView.findViewById(R.id.mWifiSignalImg) as ImageView
        private var mWifiSecurityIcon: ImageView = itemView.findViewById(R.id.mWifiSecurityImage) as ImageView

        fun bind(item: WifiData, listener: OnItemClickListener) {
            val frequency = item.frequency
            val textFrequency =
                if (frequency > FREQUENCY)
                    itemView.context.getString(R.string.high_frequency)
                else
                    itemView.context.getString(R.string.low_frequency)

            mWifiTitle.text = item.ssid
            mWifiFrequency.text = textFrequency
            mWifiSecurityIcon.visibility = if (item.isLocked) View.VISIBLE else View.INVISIBLE

            val idWifiIcon: Int = when {
                //  Wi-Fi signal values got from:
                //  https://www.metageek.com/training/resources/wifi-signal-strength-basics.html
                item.signalLevel >= BEST_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_best_signal
                item.signalLevel >= MIDDLE_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_middle_signal
                item.signalLevel >= BAD_WIFI_SIGNAL_VALUE -> R.drawable.ic_wifi_bad_signal
                else -> R.drawable.ic_wifi_worst_signal
            }
            mWifiSignalStrength.setImageResource(idWifiIcon)
            itemView.setOnClickListener { listener.onItemClick(item) }
        }
    }

    fun updateItems(items: List<WifiData>) {
        mWifiItems = items
        notifyDataSetChanged()
    }
}