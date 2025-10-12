package com.varqulabs.feature.calculator.presentation

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import com.varqulabs.dollarblue.core.conversions.data.local.CurrencyConversionDataBase
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.Ignore

@RunWith(RobolectricTestRunner::class)
@Ignore("This is used as an interface")
actual open class CalculatorIntegrationTest actual constructor()

actual abstract class RoomDataBaseTest : CalculatorIntegrationTest() {
    actual fun getInMemoryDatabaseBuilder(): RoomDatabase.Builder<CurrencyConversionDataBase> {
        val context = ApplicationProvider.getApplicationContext<Context>()
        return Room.inMemoryDatabaseBuilder(
            context = context,
            klass = CurrencyConversionDataBase::class.java,
        )
    }
}