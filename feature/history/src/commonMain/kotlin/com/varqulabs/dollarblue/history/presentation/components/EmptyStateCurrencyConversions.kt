package com.varqulabs.dollarblue.history.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.varqulabs.dollarblue.core.ui.extensions.clickableSingle
import dollarbluekmp.core.core_conversions.generated.resources.save_as_outlined
import dollarbluekmp.core.core_ui.generated.resources.arrow_forward
import dollarbluekmp.feature.history.generated.resources.Res
import dollarbluekmp.core.core_ui.generated.resources.Res as ResUi
import dollarbluekmp.core.core_conversions.generated.resources.Res as ResConversions
import dollarbluekmp.feature.history.generated.resources.calculator
import org.jetbrains.compose.resources.vectorResource

@Composable
fun EmptyStateCurrencyConversions(
    modifier: Modifier = Modifier,
    goToCalculator: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(top = 80.dp, start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Text(
            text = "Tu historial de conversiones esta vacío. \n" +
                    "Prueba guardando una conversión en la calculadora!",
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.W500,
            color = Color(0xFF004D40),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickableSingle(onClick = goToCalculator),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
        ) {

            Icon(
                imageVector = vectorResource(resource = Res.drawable.calculator),
                contentDescription = "",
                tint = Color.DarkGray,
                modifier = Modifier.size(48.dp)
            )

            Icon(
                imageVector = vectorResource(resource = ResUi.drawable.arrow_forward),
                contentDescription = "",
                tint = Color(0xFF004D40),
                modifier = Modifier.size(48.dp)
            )

            Icon(
                imageVector = vectorResource(resource = ResConversions.drawable.save_as_outlined),
                contentDescription = "",
                tint = Color.DarkGray,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}