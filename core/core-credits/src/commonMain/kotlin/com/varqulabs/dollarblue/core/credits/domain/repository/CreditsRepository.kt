package com.varqulabs.dollarblue.core.credits.domain.repository

import kotlinx.coroutines.flow.Flow

interface CreditsRepository {

    fun observeCredits(): Flow<Int>

    suspend fun getCredits(): Int

    suspend fun addCredits(credits: Int)

    suspend fun consumeCredits(credits: Int)

}