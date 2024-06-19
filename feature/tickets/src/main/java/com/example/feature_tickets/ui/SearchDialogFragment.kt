package com.example.feature_tickets.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.common.ext.setNavigationResult
import com.example.common.ext.toEditable
import com.example.common.inputfilter.CyrillicInputFilter
import com.example.feature_tickets.FROM_TEXT
import com.example.feature_tickets.R
import com.example.feature_tickets.databinding.FragmentSearchDialogBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SearchDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSearchDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initListeners()
    }

    private fun initListeners() {
        binding.btnClear.setOnClickListener {
            binding.etWhere.text = "".toEditable()
        }

        binding.btnPhuket.setOnClickListener{
            navigateToSearchCountrySelected(resources.getString(com.example.designsystem.R.string.title_phuket))
        }
        binding.btnSochi.setOnClickListener{
            navigateToSearchCountrySelected(resources.getString(com.example.designsystem.R.string.title_sochi))
        }
        binding.btnStambul.setOnClickListener{
            navigateToSearchCountrySelected(resources.getString(com.example.designsystem.R.string.title_stambul))
        }

        binding.etWhere.setOnEditorActionListener{ v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val text = binding.etWhere.text.toString()
                if (text.isNotEmpty()) {
                    navigateToSearchCountrySelected(text)
                    true
                }
                else {
                    Toast.makeText(
                        requireContext(),
                        com.example.designsystem.R.string.from_empty,
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.etWhere.clearFocus()
                    false
                }
            } else false
        }

        binding.btnSearch1.setOnClickListener{
            findNavController().navigate(R.id.action_searchDialogFragment_to_searchFragment1)
        }
        binding.btnSearch2.setOnClickListener{
            navigateToSearchCountrySelected(resources.getString(com.example.designsystem.R.string.title_search2))
        }
        binding.btnSearch3.setOnClickListener{
            findNavController().navigate(R.id.action_searchDialogFragment_to_searchFragment3)
        }
        binding.btnSearch4.setOnClickListener{
            findNavController().navigate(R.id.action_searchDialogFragment_to_searchFragment4)
        }
    }

    private fun initViews() {
        val bottomSheet: FrameLayout =
            dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
        bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.apply {
            peekHeight = resources.displayMetrics.heightPixels
            state = BottomSheetBehavior.STATE_EXPANDED
        }

        arguments?.getString(FROM_TEXT)?.let { fromText ->
            binding.etFrom.text = fromText
            binding.etFrom.setTextColor(resources.getColor(com.example.designsystem.R.color.white))
        } ?: {
            binding.etFrom.text = resources.getString(com.example.designsystem.R.string.hint_from)
            binding.etFrom.setTextColor(resources.getColor(com.example.designsystem.R.color.grey6))
        }

        binding.etWhere.filters = arrayOf(CyrillicInputFilter())
    }

    private fun navigateToSearchCountrySelected(whereText: String) {
        setNavigationResult(whereText, REQUEST_KEY)
        findNavController().popBackStack()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val REQUEST_KEY = "SEARCH_DIALOG_FRAGMENT_REQUEST_KEY"
    }

}