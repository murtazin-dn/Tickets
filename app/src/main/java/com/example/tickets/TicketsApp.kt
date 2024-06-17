package com.example.tickets

import android.app.Application
import com.example.network.di.DaggerNetworkComponent
import com.example.network.di.NetworkComponent
import com.example.tickets.di.DaggerAppComponent

class TicketsApp: Application() {

    val networkComponent: NetworkComponent by lazy {
        DaggerNetworkComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerAppComponent.builder().application(this).build()
    }
}