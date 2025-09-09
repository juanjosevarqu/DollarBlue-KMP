package com.varqulabs.dollarblue.core.credits.domain.usecase

import com.varqulabs.dollarblue.core.credits.domain.repository.CreditsRepository
import kotlinx.coroutines.flow.Flow

class ObserveCredits(
    private val repository: CreditsRepository
) {
    operator fun invoke(): Flow<Int> {
        return repository.observeCredits()
    }
}