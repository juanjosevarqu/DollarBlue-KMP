package com.varqulabs.feature.calculator.presentation.components.rates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.varqulabs.dollarblue.core.ui.launched_effect.LaunchPulseEffect
import com.varqulabs.core.common.extensions.formatWithDecimals

@Composable
fun CurrencyRateNow(
    currencyRate: Double,
    dateUpdated: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        LaunchPulseEffect(key = currencyRate) {
            Text(
                text = "1 USDT ≈ ${currencyRate.formatWithDecimals(2)} BOB",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                ),
                modifier = it,
            )
        }

        Text(
            text = dateUpdated,
            style = TextStyle(
                color = Color.Gray.copy(alpha = 0.8f),
                fontSize = 12.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            ),
        )
    }
}