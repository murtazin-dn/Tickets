package com.example.data.di

import android.app.Application
import android.content.Context
import com.example.common.mapper.Mapper
import com.example.data.mapper.ArrivalMapper
import com.example.data.mapper.DepartureMapper
import com.example.data.mapper.OfferMapper
import com.example.data.mapper.TicketMapper
import com.example.data.mapper.TicketsOfferMapper
import com.example.data.repository.CacheRepository
import com.example.data.repository.CacheSharePrefsRepository
import com.example.data.repository.DefaultOffersRepository
import com.example.data.repository.OffersRepository
import com.example.data.repository.OfflineOffersRepository
import com.example.model.offer.Offer
import com.example.model.ticket.Arrival
import com.example.model.ticket.Departure
import com.example.model.ticket.Ticket
import com.example.model.ticketoffer.TicketsOffer
import com.example.network.TicketsNetworkDataSource
import com.example.network.dto.offers.OfferDto
import com.example.network.dto.tickets.ArrivalDto
import com.example.network.dto.tickets.DepartureDto
import com.example.network.dto.tickets.TicketDto
import com.example.network.dto.ticketsoffers.TicketsOfferDto
import dagger.Module
import dagger.Provides

@Module
internal class DataModule {

    @Provides
    @DataScope
    fun provideOfferMapper(): Mapper<OfferDto, Offer> = OfferMapper()

    @Provides
    @DataScope
    fun provideTicketsOfferMapper(): Mapper<TicketsOfferDto, TicketsOffer> = TicketsOfferMapper()

    @Provides
    @DataScope
    fun provideArrivalMapper(): Mapper<ArrivalDto, Arrival> = ArrivalMapper()

    @Provides
    @DataScope
    fun provideDepartureMapper(): Mapper<DepartureDto, Departure> = DepartureMapper()

    @Provides
    @DataScope
    fun provideTicketMapper(
        arrivalMapper: Mapper<ArrivalDto, Arrival>,
        departureMapper: Mapper<DepartureDto, Departure>
    ): Mapper<TicketDto, Ticket> = TicketMapper(
        arrivalMapper,
        departureMapper
    )

    @Provides
    @DataScope
    fun provideCacheRepository(
        context: Context
    ): CacheRepository = CacheSharePrefsRepository(
        context
    )

//    @Provides
//    @DataScope
//    fun provideDefaultOffersRepository(
//        networkDataSource: TicketsNetworkDataSource,
//        ticketsOfferMapper: TicketsOfferMapper,
//        ticketMapper: TicketMapper,
//        offerMapper: OfferMapper
//    ): OffersRepository = DefaultOffersRepository(
//        ticketsNetworkDataSource = networkDataSource,
//        ticketsOfferMapper = ticketsOfferMapper,
//        ticketsMapper = ticketMapper,
//        offerMapper = offerMapper
//    )

    @Provides
    @DataScope
    fun provideOfflineOffersRepository(
        ticketsOfferMapper: TicketsOfferMapper,
        ticketMapper: TicketMapper,
        offerMapper: OfferMapper
    ): OffersRepository = OfflineOffersRepository(
        ticketsOfferMapper = ticketsOfferMapper,
        ticketsMapper = ticketMapper,
        offerMapper = offerMapper
    )
}