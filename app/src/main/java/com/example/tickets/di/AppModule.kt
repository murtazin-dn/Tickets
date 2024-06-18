package com.example.tickets.di

import com.example.domain.usecase.GetFromTextUseCase
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetTicketsOfferUseCase
import com.example.domain.usecase.GetTicketsUseCase
import com.example.domain.usecase.SaveFromTextUseCase
import com.example.feature_tickets.di.TicketsFeatureDependencies
import dagger.Module

@Module
internal class AppModule{

    @AppScope
    fun provideTicketsFeatureDependencies(
        getOffersUseCase: GetOffersUseCase,
        getTicketsOffersUseCase: GetTicketsOfferUseCase,
        getTicketsUseCase: GetTicketsUseCase,
        getFromTextUseCase: GetFromTextUseCase,
        saveFromTextUseCase: SaveFromTextUseCase,
    ): TicketsFeatureDependencies {
        return object : TicketsFeatureDependencies {
            override val providesGetOffersUseCase = getOffersUseCase
            override val providesGetTicketsOffersUseCase = getTicketsOffersUseCase
            override val providesGetTicketsUseCase = getTicketsUseCase
            override val providesGetFromTextUseCase = getFromTextUseCase
            override val providesSaveFromTextUseCase = saveFromTextUseCase
        }
    }
}