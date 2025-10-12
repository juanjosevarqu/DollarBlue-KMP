package com.varqulabs.feature.calculator.presentation

import com.varqulabs.dollarblue.core.preferences.data.repository.PreferencesRepositoryImpl
import com.varqulabs.dollarblue.core.preferences.domain.PreferencesRepository
import com.varqulabs.dollarblue.core.preferences.producePreferencesDataStore
import com.varqulabs.feature.calculator.data.local.CalculatorPreferenceKey
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okio.FileSystem
import okio.Path
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Clock

internal fun newTestPreferencesPath(): Path {
    val tmpDir: Path = FileSystem.SYSTEM_TEMPORARY_DIRECTORY
    val unique = "${Clock.System.now().toEpochMilliseconds()}"
    return (tmpDir / "dollarbluekmp-test-$unique.preferences_pb")
}

class DataStoreInstrumentedTest {

    private lateinit var repository: PreferencesRepository
    private lateinit var filePath: Path

    @BeforeTest
    fun setup() {
        filePath = newTestPreferencesPath()
        val dataStore = producePreferencesDataStore(filePath.toString())
        repository = PreferencesRepositoryImpl(dataStore)
    }

    @AfterTest
    fun tearDown() {
        runBlocking { repository.clearAllPreferences() }
    }

    @Test
    fun testing_key_bob_value_works_well() = runTest {
        val bob_value_default = repository.getNormalPreference(CalculatorPreferenceKey.BOB_VALUE, 14.0)
        assertEquals(bob_value_default, 14.0)
    }

}