package com.example.model.ticket

import com.example.model.ticket.Arrival
import com.example.model.ticket.Departure

data class Ticket(
    val id: Int,
    val badge: String? = null,
    val price: Int,
    val departure: Departure,
    val arrival: Arrival,
    val hasTransfer: Boolean
)
