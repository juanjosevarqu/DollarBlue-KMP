package com.varqulabs.feature.calculator.data.local

import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object CalculatorPreferenceKey {

    val BOB_VALUE = doublePreferencesKey("bob_value")

    val LAST_UPDATE = stringPreferencesKey("last_update")

}