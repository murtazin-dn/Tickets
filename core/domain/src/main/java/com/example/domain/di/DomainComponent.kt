package com.example.domain.di

import com.example.data.di.DataComponent
import com.example.domain.usecase.GetFromTextUseCase
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetTicketsOfferUseCase
import com.example.domain.usecase.GetTicketsUseCase
import com.example.domain.usecase.SaveFromTextUseCase
import dagger.BindsInstance
import dagger.Component

@DomainScope
@Component(modules = [DomainModule::class], dependencies = [DataComponent::class])
interface DomainComponent {

    @Component.Builder
    interface Builder{
        fun dataComponent(dataComponent: DataComponent): Builder
        fun build(): DomainComponent
    }

    val providesGetOffersUseCase: GetOffersUseCase
    val providesGetTicketsOffersUseCase: GetTicketsOfferUseCase
    val providesGetTicketsUseCase: GetTicketsUseCase
    val providesGetFromTextUseCase: GetFromTextUseCase
    val providesSaveFromTextUseCase: SaveFromTextUseCase
}