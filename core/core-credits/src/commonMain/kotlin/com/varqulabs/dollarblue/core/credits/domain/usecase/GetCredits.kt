package com.varqulabs.dollarblue.core.credits.domain.usecase

import com.varqulabs.dollarblue.core.credits.domain.repository.CreditsRepository

class GetCredits(
    private val repository: CreditsRepository
) {
    suspend operator fun invoke(): Int {
        return repository.getCredits()
    }
}