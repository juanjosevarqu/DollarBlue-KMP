package com.varqulabs.feature.calculator.presentation.components.rates

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.varqulabs.core.designsystem.white_96
import com.varqulabs.feature.calculator.domain.model.DolarRate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DolarRateSelector(
    rates: List<DolarRate>,
    modifier: Modifier = Modifier,
    onSelectRate: (DolarRate) -> Unit,
) {

    var selectedRate by remember(rates) { mutableStateOf(rates.first()) }
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {

        Row(
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = selectedRate.type.label,
                style = TextStyle(
                    color = Color.DarkGray.copy(alpha = 0.8f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                maxLines = 1
            )

            CompositionLocalProvider(LocalContentColor provides Color.DarkGray.copy(alpha = 0.8f)) {
                TrailingIcon(
                    expanded = expanded,
                    modifier = Modifier
                )
            }
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            matchTextFieldWidth = false,
            containerColor = white_96,
            shape = MaterialTheme.shapes.medium
        ) {
            rates.filter { it != selectedRate }.forEach { rate ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = rate.type.label,
                            style = TextStyle(
                                color = Color.DarkGray.copy(alpha = 0.7f),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )
                    },
                    onClick = {
                        expanded = false
                        onSelectRate(rate)
                        selectedRate = rate
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}