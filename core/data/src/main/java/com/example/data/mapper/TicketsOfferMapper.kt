package com.example.data.mapper

import com.example.common.mapper.Mapper
import com.example.model.ticketoffer.TicketsOffer
import com.example.network.dto.ticketsoffers.TicketsOfferDto
import javax.inject.Inject

class TicketsOfferMapper @Inject constructor(): Mapper<TicketsOfferDto, TicketsOffer> {
    override fun transform(entity: TicketsOfferDto) = TicketsOffer(
        id = entity.id,
        price = entity.price.value,
        title = entity.title,
        timeRange = entity.timeRange
    )
}