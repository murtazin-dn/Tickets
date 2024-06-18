package com.example.feature_tickets.adpter

import android.view.View
import com.example.common.datetime.calculateTimeDifference
import com.example.common.datetime.formatDepartureArrivalTime
import com.example.model.ticket.Ticket
import com.example.designsystem.R
import com.example.common.ext.formatIntWithSpaces
import com.example.feature_tickets.databinding.TicketItemBinding
import com.example.feature_tickets.databinding.TicketItemNoBadgeBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


fun ticketAdapterDelegate() = adapterDelegateViewBinding<Ticket, Ticket, TicketItemBinding>(
    { layoutInflater, root -> TicketItemBinding.inflate(layoutInflater, root, false) },
    { item, items, position ->
        item.badge != null
    }
) {
    bind {
        val context = binding.root.context
        val cardColor = when (item.id) {
            else -> context.resources.getColor(R.color.red)
        }


        with(binding) {
            tvBadge.text = item.badge
            tvPrice.text = context.getString(
                R.string.price_pattern2,
                item.price.formatIntWithSpaces()
            )
            cardIcon.setCardBackgroundColor(cardColor)
            tvDepartureTime.text = formatDepartureArrivalTime(item.departure.date)
            tvArrivalTime.text = formatDepartureArrivalTime(item.arrival.date)
            tvDepartureCode.text = item.departure.airport
            tvArrivalCode.text = item.arrival.airport
            if (item.hasTransfer) {
                tvTransfer.visibility = View.GONE
                tvSlash.visibility = View.GONE
            } else {
                tvTransfer.visibility = View.VISIBLE
                tvSlash.visibility = View.VISIBLE
            }
            tvTime.text = context.getString(
                R.string.title_travel_time,
                calculateTimeDifference(item.departure.date, item.arrival.date)
            )
        }
    }
}

fun ticketAdapterNoBadgeDelegate() = adapterDelegateViewBinding<Ticket, Ticket, TicketItemNoBadgeBinding>(
    { layoutInflater, root -> TicketItemNoBadgeBinding.inflate(layoutInflater, root, false) },
    { item, items, position ->
        item.badge == null
    }
) {
    bind {
        val context = binding.root.context
        val cardColor = when (item.id) {
            else -> context.resources.getColor(R.color.red)
        }


        with(binding) {
            tvPrice.text = context.getString(
                R.string.price_pattern2,
                item.price.formatIntWithSpaces()
            )
            cardIcon.setCardBackgroundColor(cardColor)
            tvDepartureTime.text = formatDepartureArrivalTime(item.departure.date)
            tvArrivalTime.text = formatDepartureArrivalTime(item.arrival.date)
            tvDepartureCode.text = item.departure.airport
            tvArrivalCode.text = item.arrival.airport
            if (item.hasTransfer) {
                tvTransfer.visibility = View.GONE
                tvSlash.visibility = View.GONE
            } else {
                tvTransfer.visibility = View.VISIBLE
                tvSlash.visibility = View.VISIBLE
            }
            tvTime.text = context.getString(
                R.string.title_travel_time,
                calculateTimeDifference(item.departure.date, item.arrival.date)
            )
        }
    }
}