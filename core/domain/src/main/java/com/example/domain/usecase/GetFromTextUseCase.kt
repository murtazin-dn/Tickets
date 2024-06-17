package com.example.domain.usecase

import com.example.data.repository.CacheRepository
import javax.inject.Inject

class GetFromTextUseCase @Inject constructor(
    private val cacheRepository: CacheRepository
) {
    fun execute(): String = cacheRepository.getFromText()
}