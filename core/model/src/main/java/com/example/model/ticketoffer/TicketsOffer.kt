package com.example.model.ticketoffer

data class TicketsOffer(
    val id: Int,
    val price: Int,
    val timeRange: List<String>,
    val title: String)