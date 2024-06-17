package com.example.data.mapper

import com.example.common.mapper.Mapper
import com.example.model.ticket.Departure
import com.example.network.dto.tickets.DepartureDto


class DepartureMapper : Mapper<DepartureDto, Departure> {
    override fun transform(entity: DepartureDto) = Departure(
        airport = entity.airport,
        date = entity.date,
        town = entity.town
    )
}