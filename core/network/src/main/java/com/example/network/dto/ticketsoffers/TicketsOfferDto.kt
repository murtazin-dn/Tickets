package com.example.network.dto.ticketsoffers

import com.example.network.dto.PriceDto


data class TicketsOfferDto(
    val id: Int,
    val price: PriceDto,
    val timeRange: List<String>,
    val title: String
)