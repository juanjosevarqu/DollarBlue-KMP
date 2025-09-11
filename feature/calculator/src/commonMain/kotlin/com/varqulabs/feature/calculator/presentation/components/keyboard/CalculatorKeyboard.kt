package com.varqulabs.feature.calculator.presentation.components.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.varqulabs.core.designsystem.greenDark_65
import com.varqulabs.feature.calculator.presentation.models.ButtonType
import com.varqulabs.feature.calculator.presentation.models.CalButton
import com.varqulabs.feature.calculator.presentation.models.CalButton.CLEAR
import com.varqulabs.feature.calculator.presentation.models.CalButton.DELETE
import com.varqulabs.feature.calculator.presentation.models.CalButton.DOUBLE_ZERO
import com.varqulabs.feature.calculator.presentation.models.CalButton.EIGHT
import com.varqulabs.feature.calculator.presentation.models.CalButton.FIVE
import com.varqulabs.feature.calculator.presentation.models.CalButton.FOUR
import com.varqulabs.feature.calculator.presentation.models.CalButton.NINE
import com.varqulabs.feature.calculator.presentation.models.CalButton.ONE
import com.varqulabs.feature.calculator.presentation.models.CalButton.PLUS
import com.varqulabs.feature.calculator.presentation.models.CalButton.SAVE
import com.varqulabs.feature.calculator.presentation.models.CalButton.SEVEN
import com.varqulabs.feature.calculator.presentation.models.CalButton.SIX
import com.varqulabs.feature.calculator.presentation.models.CalButton.SWITCH
import com.varqulabs.feature.calculator.presentation.models.CalButton.THREE
import com.varqulabs.feature.calculator.presentation.models.CalButton.TWO
import com.varqulabs.feature.calculator.presentation.models.CalButton.ZERO

private val calculatorRows = listOf(
    listOf(SEVEN, EIGHT, NINE, DELETE),
    listOf(FOUR, FIVE, SIX, PLUS),
    listOf(ONE, TWO, THREE, CLEAR),
    listOf(DOUBLE_ZERO, ZERO, SWITCH, SAVE)
)

@Composable
fun CalculatorKeyboard(
    modifier: Modifier = Modifier,
    onPressButton: (String, ButtonType) -> Unit,
) {
    Column(
        modifier = modifier.background(color = greenDark_65),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        calculatorRows.forEach { buttonRow ->
            CalculatorRow(
                buttons = buttonRow,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                onPressButton = onPressButton,
            )
        }
    }
}

@Composable
private fun CalculatorRow(
    buttons: List<CalButton>,
    modifier: Modifier = Modifier,
    onPressButton: (String, ButtonType) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        buttons.forEach {
            CalculatorButton(
                button = it,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .then(
                        if (it.type == ButtonType.DELETE) {
                            Modifier.combinedClickable(
                                onClick = { onPressButton(it.character, it.type) },
                                onLongClick = { onPressButton(it.character, CLEAR.type) }
                            )
                        } else {
                            Modifier.clickable(onClick = { onPressButton(it.character, it.type) })
                        }
                    ),
            )
        }
    }
}