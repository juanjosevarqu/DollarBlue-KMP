package com.varqulabs.dollarblue.core.credits.domain.usecase

import com.varqulabs.dollarblue.core.credits.domain.repository.CreditsRepository

class ConsumeCredits(
    private val repository: CreditsRepository
) {
    suspend operator fun invoke(credits: Int) {
        repository.consumeCredits(credits)
    }
}