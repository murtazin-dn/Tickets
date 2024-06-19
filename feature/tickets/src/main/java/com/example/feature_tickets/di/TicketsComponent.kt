package com.example.feature_tickets.di

import com.example.domain.usecase.GetFromTextUseCase
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetTicketsOfferUseCase
import com.example.domain.usecase.GetTicketsUseCase
import com.example.domain.usecase.SaveFromTextUseCase
import com.example.feature_tickets.ui.CountrySelectedFragment
import com.example.feature_tickets.ui.TicketsFragment
import com.example.feature_tickets.ui.TicketsMainFragment
import dagger.Component

@TicketsScope
@Component(modules = [TicketsModule::class], dependencies = [TicketsFeatureDependencies::class])
interface TicketsComponent{

    fun inject(ticketsMainFragment: TicketsMainFragment)
    fun inject(countrySelectedFragment: CountrySelectedFragment)
    fun inject(ticketsFragment: TicketsFragment)

    @Component.Builder
    interface Builder{
        fun ticketsFeatureDependencies(dependencies: TicketsFeatureDependencies): Builder
        fun build(): TicketsComponent
    }
}