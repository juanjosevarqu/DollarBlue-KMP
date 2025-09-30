package com.varqulabs.feature.calculator.presentation

import com.varqulabs.dollarblue.core.credits.domain.repository.CreditsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCreditsRepository : CreditsRepository {

    private var credits = 3

    var hasEnoughCredits: Boolean = true

    override fun observeCredits(): Flow<Int> {
        return flowOf(credits)
    }

    override suspend fun getCredits(): Int {
        return if (hasEnoughCredits) credits else 0
    }

    override suspend fun addCredits(credits: Int) {
        this.credits = this.credits + credits
    }

    override suspend fun consumeCredits(credits: Int) {
        this.credits = this.credits - credits
    }

}