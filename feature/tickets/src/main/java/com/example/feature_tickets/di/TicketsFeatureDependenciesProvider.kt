package com.example.feature_tickets.di

interface TicketsFeatureDependenciesProvider {
    val deps: TicketsFeatureDependencies

    companion object : TicketsFeatureDependenciesProvider by TicketsFeatureDependenciesStore
}