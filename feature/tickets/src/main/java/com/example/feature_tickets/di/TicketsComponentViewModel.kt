package com.example.feature_tickets.di

import androidx.lifecycle.ViewModel

class TicketsComponentViewModel: ViewModel() {
    val ticketsComponent = DaggerTicketsComponent.builder()
        .ticketsFeatureDependencies(TicketsFeatureDependenciesStore.deps).build()
}