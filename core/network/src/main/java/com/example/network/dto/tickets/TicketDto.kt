package com.example.network.dto.tickets

import com.example.network.dto.PriceDto

data class TicketDto(
    val arrival: ArrivalDto,
    val badge: String? = null,
    val company: String,
    val departure: DepartureDto,
    val handLuggage: HandLuggageDto,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val id: Int,
    val isExchangable: Boolean,
    val isReturnable: Boolean,
    val luggage: LuggageDto,
    val price: PriceDto,
    val providerName: String
)