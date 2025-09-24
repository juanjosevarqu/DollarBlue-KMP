package com.varqulabs.feature.calculator.presentation

import com.varqulabs.dollarblue.core.credits.domain.repository.CreditsRepository
import kotlinx.coroutines.flow.Flow

class FakeCreditsRepository : CreditsRepository {

    var hasEnoughCredits: Boolean = true

    override fun observeCredits(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getCredits(): Int {
        return if (hasEnoughCredits) 3 else 0
    }

    override suspend fun addCredits(credits: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun consumeCredits(credits: Int) {
        TODO("Not yet implemented")
    }

}