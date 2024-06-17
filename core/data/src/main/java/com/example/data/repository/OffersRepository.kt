package com.example.data.repository

import com.example.common.result.Result
import com.example.model.offer.Offer
import com.example.model.ticket.Ticket
import com.example.model.ticketoffer.TicketsOffer

interface OffersRepository {
    suspend fun getOffers(): Result<List<Offer>>
    suspend fun getTicketsOffers(): Result<List<TicketsOffer>>
    suspend fun getTickets(): Result<List<Ticket>>
}