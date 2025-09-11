package com.varqulabs.feature.calculator.presentation.components.equivalency

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.varqulabs.feature.calculator.presentation.components.rates.CurrencyRateNow
import com.varqulabs.dollarblue.core.conversions.domain.model.Currency
import com.varqulabs.core.designsystem.white_96

@Composable
fun CurrencyOutputWithRateNow(
    outputCurrency: Currency,
    outputExpression: String,
    currencyRate: Double,
    dateUpdated: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(color = white_96),
    ) {

        CurrencyOutput(
            currency = outputCurrency,
            outputExpression = outputExpression,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(start = 28.dp, end = 22.dp),
        )

        CurrencyRateNow(
            currencyRate = currencyRate,
            dateUpdated = dateUpdated,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        )
    }
}