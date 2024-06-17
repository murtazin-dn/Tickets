package com.example.data.repository

import com.example.common.mapper.Mapper
import com.example.common.network.ApiResponse
import com.example.common.network.NetworkError
import com.example.common.result.Result
import com.example.model.offer.Offer
import com.example.model.ticket.Ticket
import com.example.model.ticketoffer.TicketsOffer
import com.example.network.TicketsNetworkDataSource
import com.example.network.dto.offers.OfferDto
import com.example.network.dto.tickets.TicketDto
import com.example.network.dto.ticketsoffers.TicketsOfferDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultOffersRepository @Inject constructor(
    private val ticketsNetworkDataSource: TicketsNetworkDataSource,
    private val offerMapper: Mapper<OfferDto, Offer>,
    private val ticketsOfferMapper: Mapper<TicketsOfferDto, TicketsOffer>,
    private val ticketsMapper: Mapper<TicketDto, Ticket>
): OffersRepository {
    override suspend fun getOffers(): Result<List<Offer>> = withContext(Dispatchers.IO){
        when (val data = ticketsNetworkDataSource.getOffers()) {
            is ApiResponse.Success -> Result.success(
                data.value.offers.map { offerMapper.transform(it) })
            is ApiResponse.Error -> {
                Result.getResultFromError(data)
            }
        }
    }

    override suspend fun getTicketsOffers(): Result<List<TicketsOffer>> = withContext(Dispatchers.IO) {
        when (val data = ticketsNetworkDataSource.getTicketsOffers()) {
            is ApiResponse.Success -> Result.success(
                data.value.ticketsOffers.map { ticketsOfferMapper.transform(it) })
            is ApiResponse.Error -> {
                Result.getResultFromError(data)
            }
        }
    }

    override suspend fun getTickets(): Result<List<Ticket>> = withContext(Dispatchers.IO) {
        when (val data = ticketsNetworkDataSource.getTickets()) {
            is ApiResponse.Success -> Result.success(
                data.value.tickets.map { ticketsMapper.transform(it) })
            is ApiResponse.Error -> {
                Result.getResultFromError(data)
            }
        }
    }
}