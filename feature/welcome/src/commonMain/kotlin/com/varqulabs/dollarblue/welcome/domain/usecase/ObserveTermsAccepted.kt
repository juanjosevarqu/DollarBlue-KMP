package com.varqulabs.dollarblue.welcome.domain.usecase

import com.varqulabs.dollarblue.core.preferences.domain.PreferencesRepository
import com.varqulabs.dollarblue.welcome.data.local.WelcomePreferenceKey
import kotlinx.coroutines.flow.Flow

class ObserveTermsAccepted(
    private val repository: PreferencesRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.getPreference(
            key = WelcomePreferenceKey.TERMS_ACCEPTED,
            defaultValue = false
        )
    }
}