package com.example.testtaskavia.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.SingleLiveEvent
import com.example.common.result.Result
import com.example.domain.usecase.GetTicketsUseCase
import com.example.model.ticket.Ticket
import kotlinx.coroutines.launch
import javax.inject.Inject

class TicketsViewModel(
    private val getTicketsUseCase: GetTicketsUseCase
): ViewModel() {

    private val _tickets = MutableLiveData<List<Ticket>>()
    val tickets: LiveData<List<Ticket>> get() = _tickets
    val error = SingleLiveEvent<String?>()

    fun getTickets(){
        viewModelScope.launch {
            val result = getTicketsUseCase.execute()
            when(result){
                is Result.Error -> error.postValue(result.message)
                is Result.Success -> _tickets.value = (result.value)
            }
        }
    }
}