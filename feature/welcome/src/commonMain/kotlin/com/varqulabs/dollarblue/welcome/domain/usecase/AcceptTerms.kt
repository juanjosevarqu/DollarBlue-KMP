package com.varqulabs.dollarblue.welcome.domain.usecase

import com.varqulabs.dollarblue.core.preferences.domain.PreferencesRepository
import com.varqulabs.dollarblue.welcome.data.local.WelcomePreferenceKey

class AcceptTerms(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke() {
        repository.putPreference(
            key = WelcomePreferenceKey.TERMS_ACCEPTED,
            value = true
        )
    }
}