package com.example.project

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.example.project.utils.ConnectionManager

class MyApp: Application() {
    companion object {
        private lateinit var context: MyApp
        fun getContext(): Context {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        val connManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder().build()

        ConnectionManager.updateConnectionState(false)

        connManager.registerNetworkCallback(networkRequest, object : ConnectivityManager.NetworkCallback(){
            override fun onUnavailable() {
                super.onUnavailable()
                ConnectionManager.updateConnectionState(false)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                ConnectionManager.updateConnectionState(false)
                //showErrorDialog(context, "No network access!")
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                ConnectionManager.updateConnectionState(true)
            }
        })
    }
}