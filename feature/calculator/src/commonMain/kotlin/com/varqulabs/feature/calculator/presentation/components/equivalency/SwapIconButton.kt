package com.varqulabs.feature.calculator.presentation.components.equivalency

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.varqulabs.feature.calculator.core.white_96
import dollarbluekmp.feature.calculator.generated.resources.Res
import dollarbluekmp.feature.calculator.generated.resources.menu_swap
import org.jetbrains.compose.resources.vectorResource

@Composable
fun SwapIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = white_96
        )
    ) {
        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = white_96,
            ),
            elevation = CardDefaults.cardElevation(1.dp),
            modifier = Modifier.shadow(
                elevation = 1.dp,
                shape = CircleShape,
                ambientColor = Color(0xFF000000),
                spotColor = Color(0xFF000000),
            )
        ) {
            Icon(
                imageVector = vectorResource(resource = Res.drawable.menu_swap),
                contentDescription = "Swap Button",
                tint = Color.DarkGray
            )
        }
    }
}