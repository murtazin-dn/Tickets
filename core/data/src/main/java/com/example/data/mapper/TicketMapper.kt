package com.example.data.mapper

import com.example.common.mapper.Mapper
import com.example.model.ticket.Arrival
import com.example.model.ticket.Departure
import com.example.model.ticket.Ticket
import com.example.network.dto.tickets.ArrivalDto
import com.example.network.dto.tickets.DepartureDto
import com.example.network.dto.tickets.TicketDto
import javax.inject.Inject

class TicketMapper @Inject constructor(
    private val arrivalMapper: Mapper<ArrivalDto, Arrival>,
    private val departureMapper: Mapper<DepartureDto, Departure>
): Mapper<TicketDto, Ticket> {
    override fun transform(entity: TicketDto) = Ticket (
        id = entity.id,
        badge = entity.badge,
        price = entity.price.value,
        hasTransfer = entity.hasTransfer,
        departure = departureMapper.transform(entity.departure),
        arrival = arrivalMapper.transform(entity.arrival)
    )
}