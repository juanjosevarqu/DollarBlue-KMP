package com.varqulabs.dollarblue.history.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varqulabs.core.common.extensions.formatWithDecimals
import com.varqulabs.core.common.extensions.formatWithDecimalsBeforeRange
import com.varqulabs.core.common.extensions.formatWithoutDecimals
import com.varqulabs.core.common.extensions.toFormattedDate
import com.varqulabs.core.common.time.DatePattern.DAY_MONTH_YEAR_HYPHEN_HOUR
import com.varqulabs.core.designsystem.greenDark_65
import com.varqulabs.core.designsystem.greenDark_80
import com.varqulabs.core.designsystem.whiteCeCe
import com.varqulabs.dollarblue.core.conversions.domain.model.Currency
import com.varqulabs.dollarblue.core.conversions.domain.model.CurrencyConversion
import dollarbluekmp.feature.history.generated.resources.Res
import dollarbluekmp.feature.history.generated.resources.more_vert
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyConversionCard(
    modifier: Modifier,
    conversion: CurrencyConversion,
    onDelete: () -> Unit,
) {

    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 12.dp,
        ),
    ) {

        ExposedDropdownMenuBox(
            modifier = Modifier,
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {

            HeaderCurrencyConversion(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable),
                title = conversion.name?.takeIf { it.isNotBlank() } ?: conversion.dollarType,
                date = conversion.createdAt.toFormattedDate(pattern = DAY_MONTH_YEAR_HYPHEN_HOUR),
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                matchTextFieldWidth = true,
                containerColor = whiteCeCe,
                border = BorderStroke(width = 1.dp, color = greenDark_80),
                shape = RoundedCornerShape(8.dp),
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Eliminar",
                            style = TextStyle(
                                color = greenDark_80,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )
                    },
                    onClick = {
                        expanded = false
                        onDelete()
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        BodyCurrencyConversion(
            modifier = Modifier.fillMaxWidth(),
            dollarRate = conversion.dollarRate,
            inputCurrency = conversion.inputCurrency,
            inputValue = conversion.inputValue,
            outputCurrency = conversion.outputCurrency,
            outputValue = conversion.outputValue,
        )
    }
}

@Composable
fun HeaderCurrencyConversion(
    title: String,
    date: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(greenDark_65, greenDark_80)
                )
            )
            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 4.dp),
    ) {

        TitleAndMoreOptions(
            modifier = Modifier.fillMaxWidth(),
            title = title,
        )

        Text(
            text = date,
            style = TextStyle(
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = Color.LightGray,
            ),
            modifier = Modifier
        )
    }
}

@Composable
fun TitleAndMoreOptions(
    title: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = vectorResource(Res.drawable.more_vert),
            tint = Color.White,
            contentDescription = "More Options",
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
fun BodyCurrencyConversion(
    dollarRate: Double,
    inputCurrency: Currency,
    inputValue: Double,
    outputCurrency: Currency,
    outputValue: Double,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = 4.dp, bottom = 16.dp, start = 12.dp, end = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {

        Text(
            text = "1 USDT ≈ ${dollarRate.formatWithDecimals(2)} BOB",
            style = TextStyle(
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
            ),
        )

        HorizontalDivider(modifier = Modifier.padding(bottom = 8.dp))

        CurrencyValue(
            modifier = Modifier.fillMaxWidth(),
            currency = inputCurrency,
            value = inputValue,
            appendSymbol = true,
        )

        CurrencyValue(
            modifier = Modifier.fillMaxWidth(),
            currency = outputCurrency,
            value = outputValue,
            inputStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
            ),
            outputStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
            )
        )
    }
}

@Composable
private fun CurrencyValue(
    currency: Currency,
    value: Double,
    modifier: Modifier = Modifier,
    appendSymbol: Boolean = true,
    inputStyle: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = Color.DarkGray,
    ),
    outputStyle: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.DarkGray,
    ),
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = currency.code,
            style = inputStyle,
        )

        BasicText(
            text = if (currency == Currency.DOLLAR) "${value.formatWithDecimalsBeforeRange()} ${ if (appendSymbol) currency.symbol else "" }"
            else "${value.formatWithoutDecimals()} ${ if (appendSymbol) currency.symbol else ""}",
            style = outputStyle,
            maxLines = 1,
            autoSize = TextAutoSize.StepBased(minFontSize = 12.sp, maxFontSize = 16.sp),
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(end = 1.dp),
        )
    }
}