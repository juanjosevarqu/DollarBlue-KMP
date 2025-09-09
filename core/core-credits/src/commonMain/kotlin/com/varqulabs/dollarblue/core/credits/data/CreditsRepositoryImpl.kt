package com.varqulabs.dollarblue.core.credits.data

import com.varqulabs.dollarblue.core.credits.domain.repository.CreditsRepository
import com.varqulabs.dollarblue.core.credits.data.CreditsPreferenceKey.CREDITS
import com.varqulabs.dollarblue.core.preferences.domain.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class CreditsRepositoryImpl(
    private val preferences: PreferencesRepository,
): CreditsRepository {

    override fun observeCredits(): Flow<Int> {
        return preferences.getPreference(CREDITS, 3)
    }

    override suspend fun getCredits(): Int {
        return preferences.getNormalPreference(CREDITS, 3)
    }

    override suspend fun addCredits(credits: Int) {
        val currentCredits = getCredits()
        preferences.putPreference(CREDITS, currentCredits + credits)
    }

    override suspend fun consumeCredits(credits: Int) {
        val currentCredits = getCredits()
        preferences.putPreference(CREDITS, currentCredits - credits)
    }
}