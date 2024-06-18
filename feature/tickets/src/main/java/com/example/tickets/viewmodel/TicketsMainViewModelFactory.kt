package com.example.tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.GetFromTextUseCase
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.SaveFromTextUseCase
import javax.inject.Inject

class TicketsMainViewModelFactory @Inject constructor(
    private val getOffersUseCase: GetOffersUseCase,
    private val getFromTextUseCase: GetFromTextUseCase,
    private val saveFromTextUseCase: SaveFromTextUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TicketsMainViewModel(
            getOffersUseCase,
            getFromTextUseCase,
            saveFromTextUseCase
        ) as T
    }
}