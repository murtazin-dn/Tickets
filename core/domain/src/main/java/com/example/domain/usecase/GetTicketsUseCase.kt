package com.example.domain.usecase

import com.example.common.result.Result
import com.example.data.repository.OffersRepository
import com.example.model.ticket.Ticket
import javax.inject.Inject

class GetTicketsUseCase @Inject constructor(
    private val repository: OffersRepository
) {
    suspend fun execute(): Result<List<Ticket>> = repository.getTickets()
}