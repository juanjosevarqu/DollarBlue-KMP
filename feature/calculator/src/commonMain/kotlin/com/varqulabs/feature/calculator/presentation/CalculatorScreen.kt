package com.varqulabs.feature.calculator.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.varqulabs.core.designsystem.whiteCeCe
import com.varqulabs.dollarblue.core.conversions.domain.model.Currency.DOLLAR
import com.varqulabs.core.common.extensions.formatInputNumbers
import com.varqulabs.core.common.extensions.formatWithDecimalsBeforeRange
import com.varqulabs.core.common.extensions.formatWithoutDecimals
import com.varqulabs.core.common.extensions.formatDateCustom
import com.varqulabs.feature.calculator.domain.model.DolarRate
import com.varqulabs.dollarblue.core.ui.platform.DeviceOrientation
import com.varqulabs.dollarblue.core.ui.platform.getDeviceOrientation
import com.varqulabs.feature.calculator.presentation.CalculatorEvent.OnPressButton
import com.varqulabs.feature.calculator.presentation.CalculatorEvent.OnSelectDolarRate
import com.varqulabs.feature.calculator.presentation.CalculatorEvent.OnSwapCurrencies
import com.varqulabs.feature.calculator.presentation.components.equivalency.EquivalencyRows
import com.varqulabs.feature.calculator.presentation.components.keyboard.CalculatorKeyboard
import com.varqulabs.feature.calculator.presentation.components.rates.DolarRateSelector
import dollarbluekmp.feature.calculator.generated.resources.Res
import dollarbluekmp.feature.calculator.generated.resources.list_icon
import org.jetbrains.compose.resources.vectorResource

@Composable
fun CalculatorScreen(
    state: CalculatorState,
    eventHandler: (CalculatorEvent) -> Unit,
    goToHistory: () -> Unit,
) {

    val isPortrait = getDeviceOrientation() == DeviceOrientation.PORTRAIT

    val inputFormatted by remember(state.inputExpression) {
        derivedStateOf { state.inputExpression.formatInputNumbers(decimals = 0) }
    }
    val outputFormatted by remember(state.outputValue) {
        derivedStateOf {
            if (state.outputCurrency == DOLLAR) state.outputValue.formatWithDecimalsBeforeRange()
            else state.outputValue.formatWithoutDecimals()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = whiteCeCe)
            .windowInsetsPadding(WindowInsets.safeDrawing),
    ) {

        RateSelectorTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            dolarRates = state.dollarRates,
            goToHistory = goToHistory,
            onSelectDolarRate = { eventHandler(OnSelectDolarRate(it)) }
        )

        EquivalencyRows(
            inputCurrency = state.inputCurrency,
            outputCurrency = state.outputCurrency,
            inputExpression = inputFormatted,
            outputExpression = outputFormatted,
            currencyRate = state.dollarRate.value,
            dateUpdated = state.dateUpdated.formatDateCustom(),
            isPortrait = isPortrait,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onClickSwap = { eventHandler(OnSwapCurrencies) }
        )

        CalculatorKeyboard(
            modifier = Modifier.weight(1f),
            onPressButton = { char, type -> eventHandler(OnPressButton(char, type)) }
        )
    }
}

@Composable
private fun RateSelectorTopBar(
    dolarRates: List<DolarRate>,
    modifier: Modifier = Modifier,
    goToHistory: () -> Unit,
    onSelectDolarRate: (DolarRate) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (dolarRates.isNotEmpty()) {
            DolarRateSelector(
                rates = dolarRates,
                modifier = Modifier,
                onSelectRate = onSelectDolarRate,
            )
        } else {
            Spacer(Modifier)
        }

        IconButton(onClick = goToHistory) {
            Icon(
                imageVector = vectorResource(resource = Res.drawable.list_icon),
                contentDescription = "List History",
                tint = Color.DarkGray,
            )
        }
    }
}