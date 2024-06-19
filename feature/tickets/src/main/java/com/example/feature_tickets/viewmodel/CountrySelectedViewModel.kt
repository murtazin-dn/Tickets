package com.example.testtaskavia.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.result.Result
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetTicketsOfferUseCase
import com.example.model.ticketoffer.TicketsOffer
import kotlinx.coroutines.launch

class CountrySelectedViewModel (
    private val getTicketsOfferUseCase: GetTicketsOfferUseCase
): ViewModel() {

    private val _ticketsOffers: MutableLiveData<List<TicketsOffer>> = MutableLiveData()
    val ticketsOffers: LiveData<List<TicketsOffer>> = _ticketsOffers

    fun getOffers(){
        viewModelScope.launch {
            val result = getTicketsOfferUseCase.execute()
            when(result){
                is Result.Error -> TODO()
                is Result.Success -> _ticketsOffers.value = (result.value)
            }
        }
    }
}