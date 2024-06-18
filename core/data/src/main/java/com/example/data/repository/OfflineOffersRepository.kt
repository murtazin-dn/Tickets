package com.example.data.repository

import com.example.common.mapper.Mapper
import com.example.common.result.Result
import com.example.model.offer.Offer
import com.example.model.ticket.Ticket
import com.example.model.ticketoffer.TicketsOffer
import com.example.network.dto.offers.OfferDto
import com.example.network.dto.tickets.TicketDto
import com.example.network.dto.ticketsoffers.TicketsOfferDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OfflineOffersRepository @Inject constructor(
    private val offerMapper: Mapper<OfferDto, Offer>,
    private val ticketsOfferMapper: Mapper<TicketsOfferDto, TicketsOffer>,
    private val ticketsMapper: Mapper<TicketDto, Ticket>
): OffersRepository {

    val mock = TicketsMock()

    override suspend fun getOffers(): Result<List<Offer>> = withContext(Dispatchers.IO){
        val response = mock.getOffers()
        return@withContext Result.Success(response.offers.map { offerMapper.transform(it) })
    }

    override suspend fun getTicketsOffers(): Result<List<TicketsOffer>> = withContext(Dispatchers.IO) {
        val response = mock.getTicketsOffers()
        return@withContext Result.Success(response.ticketsOffers.map { ticketsOfferMapper.transform(it) })
    }

    override suspend fun getTickets(): Result<List<Ticket>> = withContext(Dispatchers.IO) {
        val response = mock.getTickets()
        return@withContext Result.Success(response.tickets.map { ticketsMapper.transform(it) })
    }
}