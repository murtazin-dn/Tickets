package com.example.network.dto.tickets

import com.example.network.dto.PriceDto

data class LuggageDto(
    val has_luggage: Boolean,
    val price: PriceDto? = null
)