package com.varqulabs.dollarblue.history.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varqulabs.core.designsystem.greenDark_65
import com.varqulabs.core.designsystem.greenDark_80
import dollarbluekmp.feature.history.generated.resources.Res
import dollarbluekmp.feature.history.generated.resources.add_circle
import org.jetbrains.compose.resources.vectorResource

@Composable
fun CurrentCredits(
    credits: Int,
    modifier: Modifier = Modifier,
    onClickCredits: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        SuggestionChip(
            onClick = onClickCredits,
            label = {
                Text(
                    text = "$credits Créditos Restantes",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            },
            shape = RoundedCornerShape(50),
            colors = SuggestionChipDefaults.suggestionChipColors(
                labelColor = greenDark_65
            ),
            border = SuggestionChipDefaults.suggestionChipBorder(
                enabled = true,
                borderColor = greenDark_65,
                borderWidth = 1.dp,
            )
        )

        IconButton(
            onClick = onClickCredits,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = vectorResource(Res.drawable.add_circle),
                contentDescription = "Agregar",
                tint = greenDark_80,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}