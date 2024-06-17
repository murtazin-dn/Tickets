package com.example.network.di

import com.example.network.TicketsNetworkDataSource
import dagger.Component

@NetworkScope
@Component(modules = [NetworkModule::class])
interface NetworkComponent {
    val dataSource: TicketsNetworkDataSource
}