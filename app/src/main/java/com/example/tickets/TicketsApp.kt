package com.example.tickets

import android.app.Application
import com.example.data.di.DaggerDataComponent
import com.example.data.di.DataComponent
import com.example.domain.di.DaggerDomainComponent
import com.example.domain.di.DomainComponent
import com.example.feature_tickets.di.TicketsFeatureDependenciesStore
import com.example.network.di.DaggerNetworkComponent
import com.example.network.di.NetworkComponent
import com.example.tickets.di.DaggerAppComponent

class TicketsApp: Application() {

    private val networkComponent: NetworkComponent by lazy {
        DaggerNetworkComponent.builder().build()
    }
    private val dataComponent: DataComponent by lazy {
        DaggerDataComponent.builder().context(this).networkComponent(networkComponent).build()
    }
    private val domainComponent: DomainComponent by lazy {
        DaggerDomainComponent.builder().dataComponent(dataComponent).build()
    }

    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerAppComponent.builder().domainComponent(domainComponent).build()
        TicketsFeatureDependenciesStore.deps = appComponent
    }
}