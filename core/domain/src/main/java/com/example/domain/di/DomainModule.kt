package com.example.domain.di

import com.example.data.repository.CacheRepository
import com.example.data.repository.OffersRepository
import com.example.domain.usecase.GetFromTextUseCase
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetTicketsOfferUseCase
import com.example.domain.usecase.GetTicketsUseCase
import com.example.domain.usecase.SaveFromTextUseCase
import dagger.Module
import dagger.Provides

@Module
internal class DomainModule {

    @Provides
    @DomainScope
    fun providesGetOffersUseCase(offersRepository: OffersRepository): GetOffersUseCase =
        GetOffersUseCase(offersRepository)
    @Provides
    @DomainScope
    fun providesGetTicketsOffersUseCase(offersRepository: OffersRepository): GetTicketsOfferUseCase =
        GetTicketsOfferUseCase(offersRepository)
    @Provides
    @DomainScope
    fun providesGetTicketsUseCase(offersRepository: OffersRepository): GetTicketsUseCase =
        GetTicketsUseCase(offersRepository)
    @Provides
    @DomainScope
    fun providesGetFromTextUseCase(cacheRepository: CacheRepository): GetFromTextUseCase =
        GetFromTextUseCase(cacheRepository)
    @Provides
    @DomainScope
    fun providesSaveFromTextUseCase(cacheRepository: CacheRepository): SaveFromTextUseCase =
        SaveFromTextUseCase(cacheRepository)
}