package com.example.roman.wifiscanner.wifi

import android.content.BroadcastReceiver

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import com.example.roman.wifiscanner.wifi.wifidataclass.WifiData
import com.example.roman.wifiscanner.wifi.wifistate.WifiState
import com.example.roman.wifiscanner.wifi.wifistate.WifiStateEvent
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposables

import javax.inject.Inject

class ScannerService @Inject constructor(private val mContext: Context) : IWifiScanner {

    companion object {
        private val SECURITY_MODES = arrayOf("WEP", "WPA", "WPA2", "WPA_EAP", "IEEE8021X")
    }

    override fun scan(): Single<List<WifiData>> {
        return Single.create<List<ScanResult>> { emitter ->

            val wifiManager = mContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            wifiManager.startScan()

            val receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    var results = wifiManager.scanResults as ArrayList<ScanResult>
                    val unique: HashMap<String, ScanResult> = HashMap()
                    for (scanItem in results) {
                        val prevScanResult = unique[scanItem.SSID]
                        if (prevScanResult != null) {
                            unique[prevScanResult.SSID] = if (prevScanResult.level > scanItem.level) {
                                prevScanResult
                            } else {
                                scanItem
                            }
                        } else {
                            unique[scanItem.SSID] = scanItem
                        }
                    }
                    results = ArrayList(unique.values)
                    emitter.onSuccess(results)
                }
            }
            mContext.registerReceiver(receiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
            emitter.setDisposable(Disposables.fromAction {
                mContext.unregisterReceiver(receiver)
            })
        }
            .flattenAsObservable { results -> results }
            .map { result ->
                val isLocked = SECURITY_MODES.find { item -> result.capabilities.contains(item) } != null
                WifiData(result.SSID, isLocked)
            }.toList()
    }

    override fun observeWiFiOnOffStatus(): Observable<WifiStateEvent> {
        return Observable.create<WifiStateEvent> { emitter ->
            val wifiManager = mContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val wifiOnOffState = wifiManager.isWifiEnabled
                    val event =
                        if (wifiOnOffState) {
                            WifiStateEvent(WifiState.ON)
                        } else {
                            WifiStateEvent(WifiState.OFF)
                        }
                    emitter.onNext(event)
                }
            }
            mContext.registerReceiver(receiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
            emitter.setDisposable(Disposables.fromAction
            {
                mContext.unregisterReceiver(receiver)
            })
        }
    }

    override fun observeWiFiConnectionStatus(): Observable<WifiStateEvent> {
        return Observable.create<WifiStateEvent> { emitter ->
            val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val networkInfo = connectivityManager.activeNetworkInfo
                    val event =
                        if (networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                            WifiStateEvent(WifiState.CONNECTED)
                        } else {
                            WifiStateEvent(WifiState.DISCONNECTED)
                        }
                    emitter.onNext(event)
                }
            }
            mContext.registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
            emitter.setDisposable(
                Disposables.fromAction
                {
                    mContext.unregisterReceiver(receiver)
                })
        }
    }

    override fun checkWifiStatus(): Observable<WifiStateEvent> {
        return observeWiFiOnOffStatus()
            .switchMap { value ->
                if (value == WifiStateEvent(WifiState.ON)) {
                    observeWiFiConnectionStatus()
                } else {
                    Observable.empty()
                }
            }
    }
}