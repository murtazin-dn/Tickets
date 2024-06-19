package com.example.feature_tickets.ui

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.datetime.calendarToDateIso
import com.example.common.datetime.formatDate
import com.example.common.ext.toEditable
import com.example.feature_tickets.DATE
import com.example.feature_tickets.FROM_TEXT
import com.example.feature_tickets.PASSENGERS_COUNT
import com.example.feature_tickets.R
import com.example.feature_tickets.WHERE_TEXT
import com.example.feature_tickets.adpter.TicketsOffersAdapter
import com.example.feature_tickets.databinding.FragmentCountrySelectedBinding
import com.example.feature_tickets.di.TicketsComponentViewModel
import com.example.testtaskavia.presentation.viewmodels.CountrySelectedViewModel
import com.example.testtaskavia.presentation.viewmodels.CountrySelectedViewModelFactory
import com.google.android.material.chip.Chip
import java.util.Calendar
import javax.inject.Inject

class CountrySelectedFragment : Fragment() {

    private var _binding: FragmentCountrySelectedBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: CountrySelectedViewModelFactory
    private lateinit var viewModel: CountrySelectedViewModel

    private lateinit var ticketsOffersAdapter: TicketsOffersAdapter

    private var calendar1: Calendar = Calendar.getInstance()
    private var calendar2: Calendar = Calendar.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ViewModelProvider(this).get(TicketsComponentViewModel::class.java).ticketsComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CountrySelectedViewModel::class.java)
        viewModel.getOffers()
        _binding = FragmentCountrySelectedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initListeners() {
        viewModel.ticketsOffers.observe(viewLifecycleOwner) { ticketsOffers ->
            ticketsOffersAdapter.data = ticketsOffers
        }
        binding.chipBack.setOnClickListener{it as Chip
            showDatePickerDialog(calendar1) { calendar ->
                calendar1 = calendar
                it.text = formatDate(calendar)
            }
        }
        binding.chipDate.setOnClickListener{it as Chip
            showDatePickerDialog(calendar2) { calendar ->
                calendar2 = calendar
                it.text = formatDate(calendar)
                it.isChipIconVisible = false
            }
        }
        binding.btnChange.setOnClickListener{reversePlaceText()}
        binding.btnClear.setOnClickListener{binding.etWhere.text = "".toEditable()}
        binding.btnBack.setOnClickListener{findNavController().popBackStack()}
        binding.btnShowTickets.setOnClickListener{
            val fromText = binding.etFrom.text.toString()
            val whereText = binding.etWhere.text.toString()
            val bundle = bundleOf(
                FROM_TEXT to fromText,
                WHERE_TEXT to whereText,
                DATE to calendarToDateIso(calendar2),
                PASSENGERS_COUNT to 1
            )
            findNavController().navigate(
                R.id.action_countrySelectedFragment2_to_ticketsFragment,
                bundle
            )
        }
    }

    private fun initViews() {
        val layoutManager = LinearLayoutManager(requireContext())
        ticketsOffersAdapter = TicketsOffersAdapter()
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = ticketsOffersAdapter

        val fromText = arguments?.getString(FROM_TEXT, null)
            ?: resources.getString(com.example.designsystem.R.string.moskva)
        val whereText = arguments?.getString(WHERE_TEXT, null)
            ?: resources.getString(com.example.designsystem.R.string.turcia)
        binding.etFrom.text = fromText.toEditable()
        binding.etWhere.text = whereText.toEditable()

        binding.chipDate.text = formatDate(calendar2)
    }

    private fun reversePlaceText(){
        val fromText = binding.etFrom.text
        val whereText = binding.etWhere.text
        binding.etFrom.text = whereText
        binding.etWhere.text = fromText
    }

    private fun showDatePickerDialog(calendar: Calendar, action: (Calendar) -> Unit) {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, day ->
            val result = Calendar.getInstance().apply{
                set(year, month, day)
            }
            action.invoke(result)
        }, year, month, day)

        datePickerDialog.show()
    }

}