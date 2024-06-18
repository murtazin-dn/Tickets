package com.example.tickets.di

import com.example.domain.usecase.GetFromTextUseCase
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetTicketsOfferUseCase
import com.example.domain.usecase.GetTicketsUseCase
import com.example.domain.usecase.SaveFromTextUseCase
import dagger.Component

@TicketsScope
@Component(modules = [TicketsModule::class], dependencies = [TicketsFeatureDependencies::class])
interface TicketsComponent{

    @Component.Builder
    interface Builder{
        fun ticketsFeatureDependencies(dependencies: TicketsFeatureDependencies): Builder
        fun build(): TicketsComponent
    }
}