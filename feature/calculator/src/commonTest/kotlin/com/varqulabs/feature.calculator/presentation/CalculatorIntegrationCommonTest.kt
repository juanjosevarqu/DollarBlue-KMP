package com.varqulabs.feature.calculator.presentation

import androidx.room.RoomDatabase
import com.varqulabs.dollarblue.core.conversions.data.local.CurrencyConversionDao
import com.varqulabs.dollarblue.core.conversions.data.local.CurrencyConversionDataBase
import com.varqulabs.dollarblue.core.conversions.data.local.CurrencyConversionEntity
import com.varqulabs.dollarblue.core.conversions.data.repository.CurrencyConversionRepositoryImpl
import com.varqulabs.dollarblue.core.conversions.domain.CurrencyConversionRepository
import com.varqulabs.dollarblue.core.conversions.domain.model.Currency
import com.varqulabs.dollarblue.core.credits.domain.usecase.ConsumeCredits
import com.varqulabs.dollarblue.core.credits.domain.usecase.GetCredits
import com.varqulabs.feature.calculator.data.repository.FakeBolivianUSDTRepository
import com.varqulabs.feature.calculator.domain.usecase.bolivian_usdt.GetBolivianUSDT
import com.varqulabs.feature.calculator.domain.usecase.currency_conversion.SaveConversion
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.Clock

expect open class CalculatorIntegrationTest()

expect abstract class RoomDataBaseTest() : CalculatorIntegrationTest {
    fun getInMemoryDatabaseBuilder(): RoomDatabase.Builder<CurrencyConversionDataBase>
}

@OptIn(ExperimentalCoroutinesApi::class)
class CalculatorIntegrationCommonTest : RoomDataBaseTest() {

    val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var database: CurrencyConversionDataBase
    private lateinit var conversion: CurrencyConversionEntity
    private lateinit var conversionDao: CurrencyConversionDao
    private lateinit var repository: CurrencyConversionRepository

    private lateinit var viewModel: CalculatorViewModel

    private lateinit var fakeBolivianUSDTRepository: FakeBolivianUSDTRepository
    private lateinit var fakeCreditsRepository: FakeCreditsRepository

    private lateinit var getBolivianUSDT: GetBolivianUSDT
    private lateinit var getCredits: GetCredits
    private lateinit var saveConversion: SaveConversion
    private lateinit var consumeCredits: ConsumeCredits

    @BeforeTest
    fun setup() {
        database = getInMemoryDatabaseBuilder().build()
        conversionDao = database.currencyConversionDao()
        repository = CurrencyConversionRepositoryImpl(conversionDao)

        fakeBolivianUSDTRepository = FakeBolivianUSDTRepository()
        fakeCreditsRepository = FakeCreditsRepository()

        getBolivianUSDT = GetBolivianUSDT(fakeBolivianUSDTRepository)
        getCredits = GetCredits(fakeCreditsRepository)
        saveConversion = SaveConversion(repository)
        consumeCredits = ConsumeCredits(fakeCreditsRepository)

        viewModel = CalculatorViewModel(
            getBolivianUSDT = getBolivianUSDT,
            getCredits = getCredits,
            saveConversion = saveConversion,
            consumeCredits = consumeCredits,
            dispatcher = testDispatcher,
        )

        conversion = CurrencyConversionEntity(
            localId = 1L,
            conversionId = "test-uuid-123",
            dollarType = "Dolar USDT - Venta",
            dollarRate = 15.0,
            inputValue = 100.0,
            outputValue = 1500.0,
            inputCurrency = Currency.DOLLAR.code,
            outputCurrency = Currency.BOLIVIANO.code,
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now(),
            name = "Fake Conversion",
            normalizedName = "fake conversion"
        )
    }

    @AfterTest
    fun tearDown() {
        database.close()
    }

    @Test
    fun test_save_conversion() = runTest {
        viewModel.eventHandler(CalculatorEvent.OnSaveConversion(conversion.name.orEmpty()))

        val userSaved = repository.getAllSortedByMostRecent().first().first()
        assertTrue(userSaved.name == "Fake Conversion")
    }


}
