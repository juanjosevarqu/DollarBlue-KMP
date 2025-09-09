package com.varqulabs.feature.calculator.presentation.components.equivalency

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.varqulabs.dollarblue.core.conversions.domain.model.Currency
import dollarbluekmp.feature.calculator.generated.resources.Res
import dollarbluekmp.feature.calculator.generated.resources.rounded_arrow_drop_down
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun CurrencyBox(
    currency: Currency,
    fontSize: TextUnit,
    iconSize: Dp,
    modifier : Modifier = Modifier,
    color: Color = Color.DarkGray,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(resource = currency.symbolImage),
            contentDescription = "Simbolo",
            modifier = Modifier.size(iconSize),
            colorFilter = ColorFilter.tint(color)
        )

        Text(
            text = currency.code,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
            color = color
        )

        Icon(
            imageVector = vectorResource(Res.drawable.rounded_arrow_drop_down),
            contentDescription = "DropDown Currency",
            tint = color,
            modifier = Modifier.size(iconSize)
        )
    }
}