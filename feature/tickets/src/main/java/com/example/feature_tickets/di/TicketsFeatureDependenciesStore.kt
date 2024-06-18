package com.example.feature_tickets.di

import kotlin.properties.Delegates.notNull

object TicketsFeatureDependenciesStore: TicketsFeatureDependenciesProvider {
    override var deps: TicketsFeatureDependencies by notNull()
}