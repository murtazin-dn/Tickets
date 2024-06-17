package com.example.network

import com.example.common.network.ApiResponse
import com.example.network.dto.offers.OffersDto
import com.example.network.dto.tickets.TicketsDto
import com.example.network.dto.ticketsoffers.TicketsOffersDto

interface TicketsNetworkDataSource {
    suspend fun getOffers(): ApiResponse<OffersDto>
    suspend fun getTickets(): ApiResponse<TicketsDto>
    suspend fun getTicketsOffers(): ApiResponse<TicketsOffersDto>
}