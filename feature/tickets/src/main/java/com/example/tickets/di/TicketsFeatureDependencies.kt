package com.example.tickets.di

import com.example.domain.usecase.GetFromTextUseCase
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetTicketsOfferUseCase
import com.example.domain.usecase.GetTicketsUseCase
import com.example.domain.usecase.SaveFromTextUseCase

interface TicketsFeatureDependencies {
    val providesGetOffersUseCase: GetOffersUseCase
    val providesGetTicketsOffersUseCase: GetTicketsOfferUseCase
    val providesGetTicketsUseCase: GetTicketsUseCase
    val providesGetFromTextUseCase: GetFromTextUseCase
    val providesSaveFromTextUseCase: SaveFromTextUseCase
}