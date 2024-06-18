package com.example.tickets.di

import android.app.Application
import com.example.domain.di.DomainComponent
import com.example.domain.usecase.GetFromTextUseCase
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetTicketsOfferUseCase
import com.example.domain.usecase.GetTicketsUseCase
import com.example.domain.usecase.SaveFromTextUseCase
import com.example.feature_tickets.di.TicketsFeatureDependencies
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [AppModule::class], dependencies = [DomainComponent::class])
interface AppComponent: TicketsFeatureDependencies {

    override val providesGetOffersUseCase: GetOffersUseCase
    override val providesGetTicketsOffersUseCase: GetTicketsOfferUseCase
    override val providesGetTicketsUseCase: GetTicketsUseCase
    override val providesGetFromTextUseCase: GetFromTextUseCase
    override val providesSaveFromTextUseCase: SaveFromTextUseCase

    @Component.Builder
    interface Builder{
        fun domainComponent(domainComponent: DomainComponent): Builder
        fun build(): AppComponent
    }
}