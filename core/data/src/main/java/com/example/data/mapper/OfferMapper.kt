package com.example.data.mapper

import com.example.common.mapper.Mapper
import com.example.model.offer.Offer
import com.example.network.dto.offers.OfferDto
import javax.inject.Inject

class OfferMapper @Inject constructor(): Mapper<OfferDto, Offer> {
    override fun transform(entity: OfferDto) = Offer(
        id = entity.id,
        price = entity.price.value,
        title = entity.title,
        town = entity.town
    )
}