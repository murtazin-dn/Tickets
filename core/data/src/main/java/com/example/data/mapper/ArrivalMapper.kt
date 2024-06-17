package com.example.data.mapper

import com.example.common.mapper.Mapper
import com.example.model.ticket.Arrival
import com.example.network.dto.tickets.ArrivalDto

class ArrivalMapper: Mapper<ArrivalDto, Arrival> {
    override fun transform(entity: ArrivalDto) = Arrival (
        airport = entity.airport,
        date = entity.date,
        town = entity.town
    )
}