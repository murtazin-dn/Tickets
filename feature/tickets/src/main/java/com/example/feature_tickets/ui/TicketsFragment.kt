package com.example.feature_tickets.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.datetime.formatDate2
import com.example.feature_tickets.DATE
import com.example.feature_tickets.FROM_TEXT
import com.example.feature_tickets.PASSENGERS_COUNT
import com.example.feature_tickets.WHERE_TEXT
import com.example.feature_tickets.adpter.ticketAdapterDelegate
import com.example.feature_tickets.adpter.ticketAdapterNoBadgeDelegate
import com.example.feature_tickets.databinding.FragmentTicketsBinding
import com.example.feature_tickets.di.TicketsComponentViewModel
import com.example.model.ticket.Ticket
import com.example.testtaskavia.presentation.viewmodels.TicketsViewModel
import com.example.testtaskavia.presentation.viewmodels.TicketsViewModelFactory
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import javax.inject.Inject

class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: TicketsViewModelFactory
    private lateinit var viewModel: TicketsViewModel

    private lateinit var adapter: ListDelegationAdapter<List<Ticket>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ViewModelProvider(this).get(TicketsComponentViewModel::class.java).ticketsComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(TicketsViewModel::class.java)
        viewModel.getTickets()
        _binding = FragmentTicketsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListDelegationAdapter(
            ticketAdapterDelegate(),
            ticketAdapterNoBadgeDelegate()
        )
        binding.rcTickets.adapter = adapter
        binding.rcTickets.layoutManager = LinearLayoutManager(requireContext())
        viewModel.tickets.observe(viewLifecycleOwner){ tickets ->
            adapter.items = tickets
            adapter.notifyDataSetChanged()
        }

        val fromText = arguments?.getString(FROM_TEXT, null)
            ?: resources.getString(com.example.designsystem.R.string.moskva)
        val whereText = arguments?.getString(WHERE_TEXT, null)
            ?: resources.getString(com.example.designsystem.R.string.turcia)
        val passengersCount = arguments?.getInt(PASSENGERS_COUNT) ?: 1
        val date = formatDate2(arguments?.getString(DATE) ?: "2024-02-23T12:00:00")

        val title = "$fromText - $whereText"
        val subTitle = "$date, $passengersCount пассажир"

        binding.toolbar.title = title
        binding.toolbar.subtitle = subTitle
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }



}