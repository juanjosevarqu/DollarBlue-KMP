package com.varqulabs.feature.calculator.presentation.components.equivalency

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varqulabs.dollarblue.core.conversions.domain.model.Currency

@Composable
fun CurrencyOutput(
    currency: Currency,
    outputExpression: String,
    modifier: Modifier = Modifier,
    fontStyle: TextStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.DarkGray.copy(alpha = 0.6f),
    ),
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        CurrencyBox(
            currency = currency,
            fontSize = fontStyle.fontSize,
            iconSize = 14.dp,
            color = fontStyle.color,
        )

        Text(
            text = outputExpression,
            style = fontStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )

        Text(
            text = currency.symbol,
            style = fontStyle,
        )
    }
}