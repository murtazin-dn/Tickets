package com.example.feature_tickets.adpter

import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.common.datetime.calculateTimeDifference
import com.example.common.datetime.formatDepartureArrivalTime
import com.example.designsystem.R
import com.example.model.ticket.Ticket
import com.example.common.ext.formatIntWithSpaces
import com.example.feature_tickets.databinding.TicketItemBinding

class TicketsAdapter: RecyclerView.Adapter<TicketsAdapter.TicketsViewHolder>() {

    var data: List<Ticket> = listOf()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }
    class TicketsViewHolder(val binding: TicketItemBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TicketItemBinding.inflate(inflater, parent, false)

        return TicketsViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        val ticket = data[position]
        val context = holder.itemView.context
        val cardColor = when(ticket.id){
            else -> context.resources.getColor(R.color.red)
        }


        with(holder.binding){
            if(ticket.badge != null) {
                tvBadge.text = ticket.badge
                tvBadge.visibility = View.VISIBLE
            } else {
                tvBadge.visibility = View.GONE
            }
            tvPrice.text = context.getString(R.string.price_pattern2, ticket.price.formatIntWithSpaces())
            cardIcon.setCardBackgroundColor(cardColor)
            tvDepartureTime.text = formatDepartureArrivalTime(ticket.departure.date)
            tvArrivalTime.text = formatDepartureArrivalTime(ticket.arrival.date)
            tvDepartureCode.text = ticket.departure.airport
            tvArrivalCode.text = ticket.arrival.airport
            if(ticket.hasTransfer){
                tvTransfer.visibility = View.GONE
                tvSlash.visibility = View.GONE
            } else {
                tvTransfer.visibility = View.VISIBLE
                tvSlash.visibility = View.VISIBLE
            }
            tvTime.text = context.getString(
                R.string.title_travel_time,
                calculateTimeDifference(ticket.departure.date, ticket.arrival.date)
            )
        }
    }
}