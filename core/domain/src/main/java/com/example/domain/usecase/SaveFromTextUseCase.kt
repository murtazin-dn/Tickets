package com.example.domain.usecase

import com.example.data.repository.CacheRepository
import javax.inject.Inject


class SaveFromTextUseCase @Inject constructor(
    private val cacheRepository: CacheRepository
) {
    fun execute(fromText: String) = cacheRepository.saveFromText(fromText)
}