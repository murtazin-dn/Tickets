package com.example.network.dto.offers

import com.example.network.dto.PriceDto

data class OfferDto(
    val id: Int,
    val price: PriceDto,
    val title: String,
    val town: String
)