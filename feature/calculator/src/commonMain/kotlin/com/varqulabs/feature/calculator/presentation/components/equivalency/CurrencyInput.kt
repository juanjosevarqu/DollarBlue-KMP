package com.varqulabs.feature.calculator.presentation.components.equivalency

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varqulabs.core.designsystem.greenDark_45
import com.varqulabs.dollarblue.core.conversions.domain.model.Currency
import dollarbluekmp.feature.calculator.generated.resources.Res
import dollarbluekmp.feature.calculator.generated.resources.current_input
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource

@Composable
fun CurrencyInput(
    currency: Currency,
    inputExpression: String,
    modifier: Modifier = Modifier,
    fontStyle: TextStyle = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.DarkGray,
        textAlign = TextAlign.End,
    ),
) {

    var showCursor by remember { mutableStateOf(true) }
    val currentInputSemantics = stringResource(Res.string.current_input)

    LaunchedEffect(Unit) {
        while (true) {
            delay(600L)
            showCursor = !showCursor
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        CurrencyBox(
            currency = currency,
            fontSize = fontStyle.fontSize,
            iconSize = 18.dp,
            color = fontStyle.color,
        )

        Box(
            modifier = Modifier.weight(1f).heightIn(min = 38.dp),
            contentAlignment = Alignment.CenterEnd
        ) {

            BasicText(
                text = inputExpression,
                style = fontStyle,
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(minFontSize = 20.sp, maxFontSize = fontStyle.fontSize),
                overflow = TextOverflow.StartEllipsis,
                modifier = Modifier.padding(end = 1.dp).semantics { contentDescription = currentInputSemantics },
            )

            if (showCursor) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(fontStyle.fontSize.value.dp)
                        .background(color = greenDark_45)
                )
            }
        }
    }
}