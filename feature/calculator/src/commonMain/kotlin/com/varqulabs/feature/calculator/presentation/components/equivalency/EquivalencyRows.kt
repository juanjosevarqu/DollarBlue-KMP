package com.varqulabs.feature.calculator.presentation.components.equivalency

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.varqulabs.dollarblue.core.conversions.domain.model.Currency

@Composable
fun EquivalencyRows(
    inputCurrency: Currency,
    outputCurrency: Currency,
    inputExpression: String,
    outputExpression: String,
    currencyRate: Double,
    dateUpdated: String,
    isPortrait: Boolean,
    modifier: Modifier = Modifier,
    onClickSwap: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        CurrencyRows(
            inputCurrency = inputCurrency,
            outputCurrency = outputCurrency,
            inputExpression = inputExpression,
            outputExpression = outputExpression,
            currencyRate = currencyRate,
            dateUpdated = dateUpdated,
            isPortrait = isPortrait,
            modifier = Modifier.fillMaxWidth(),
        )

        SwapIconButton(onClick = onClickSwap)
    }
}

@Composable
private fun CurrencyRows(
    inputCurrency: Currency,
    outputCurrency: Currency,
    inputExpression: String,
    outputExpression: String,
    currencyRate: Double,
    dateUpdated: String,
    isPortrait: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {

        CurrencyInput(
            currency = inputCurrency,
            inputExpression = inputExpression,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 22.dp, vertical = if (isPortrait) 28.dp else 0.dp),
        )

        CurrencyOutputWithRateNow(
            outputCurrency = outputCurrency,
            outputExpression = outputExpression,
            currencyRate = currencyRate,
            dateUpdated = dateUpdated,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        )
    }
}