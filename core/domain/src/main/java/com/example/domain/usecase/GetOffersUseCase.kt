package com.example.domain.usecase

import com.example.common.result.Result
import com.example.data.repository.OffersRepository
import com.example.model.offer.Offer
import javax.inject.Inject

class GetOffersUseCase @Inject constructor(
    private val repository: OffersRepository
) {
    suspend fun execute(): Result<List<Offer>> = repository.getOffers()
}