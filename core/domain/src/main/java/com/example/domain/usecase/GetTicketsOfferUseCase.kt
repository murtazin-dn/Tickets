package com.example.domain.usecase

import com.example.common.result.Result
import com.example.data.repository.OffersRepository
import com.example.model.ticketoffer.TicketsOffer
import javax.inject.Inject


class GetTicketsOfferUseCase @Inject constructor(
    private val offersRepository: OffersRepository
) {
    suspend fun execute(): Result<List<TicketsOffer>> =
        offersRepository.getTicketsOffers()
}