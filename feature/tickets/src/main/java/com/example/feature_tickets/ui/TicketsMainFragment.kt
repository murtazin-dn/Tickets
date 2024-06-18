package com.example.feature_tickets.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.ext.registerResultObserver
import com.example.common.ext.toEditable
import com.example.common.inputfilter.CyrillicInputFilter
import com.example.feature_tickets.FROM_TEXT
import com.example.feature_tickets.R
import com.example.feature_tickets.WHERE_TEXT
import com.example.feature_tickets.adpter.OffersAdapter
import com.example.feature_tickets.databinding.FragmentTicketsMainBinding
import com.example.feature_tickets.di.DaggerTicketsComponent
import com.example.feature_tickets.di.TicketsComponent
import com.example.feature_tickets.di.TicketsComponentViewModel
import com.example.feature_tickets.di.TicketsFeatureDependenciesStore
import com.example.feature_tickets.viewmodel.TicketsMainViewModel
import com.example.feature_tickets.viewmodel.TicketsMainViewModelFactory
import javax.inject.Inject

class TicketsMainFragment : Fragment() {

    private var _binding: FragmentTicketsMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: TicketsMainViewModelFactory
    private lateinit var viewModel: TicketsMainViewModel
    private lateinit var offersAdapter: OffersAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this).get(TicketsComponentViewModel::class.java)
            .ticketsComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(TicketsMainViewModel::class.java)
        viewModel.getOffers()
        viewModel.getFromText()
        registerResultObserver<String>(SearchDialogFragment.REQUEST_KEY) { whereText ->
            binding.etWhere.text = whereText.toEditable()
            val fromText = binding.etFrom.text.toString()
            val bundle = bundleOf(
                FROM_TEXT to fromText,
                WHERE_TEXT to whereText
            )
//            findNavController().navigate(
//                R.id.action_navigation_air_tickets_to_countrySelectedFragment2,
//                bundle
//            )
        }
        _binding = FragmentTicketsMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(requireContext())
            .apply { orientation = LinearLayoutManager.HORIZONTAL }
        offersAdapter = OffersAdapter()
        binding.rcOffers.layoutManager = layoutManager
        binding.rcOffers.adapter = offersAdapter

        binding.etFrom.filters = arrayOf(CyrillicInputFilter())
        binding.etFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                viewModel.saveFromText(p0.toString())
            }
        })
        binding.etWhere.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val fromText = binding.etFrom.text.toString()
                val bundle =
                    if (fromText.isNotEmpty()) bundleOf(FROM_TEXT to fromText)
                    else {
                        Toast.makeText(
                            requireContext(),
                            "Поле \"Откуда\" не может быть пустым",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.etWhere.clearFocus()
                        return@setOnFocusChangeListener
                    }
                binding.etWhere.clearFocus()
                findNavController()
                    .navigate(R.id.action_navigation_air_tickets_to_searchDialogFragment, bundle)
            }
        }
    }

    private fun initObservers() {
        viewModel.offers.observe(viewLifecycleOwner) { offers ->
            println(offers)
            offersAdapter.data = offers
        }
        viewModel.fromText.observe(viewLifecycleOwner) { fromText ->
            binding.etFrom.text = fromText.toEditable()
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), com.example.designsystem.R.string.error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}