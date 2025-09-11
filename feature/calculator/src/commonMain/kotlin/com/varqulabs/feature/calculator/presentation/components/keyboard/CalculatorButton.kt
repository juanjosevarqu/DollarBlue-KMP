package com.varqulabs.feature.calculator.presentation.components.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varqulabs.core.designsystem.whiteOpacity_80
import com.varqulabs.feature.calculator.presentation.models.ButtonType
import com.varqulabs.feature.calculator.presentation.models.CalButton
import dollarbluekmp.feature.calculator.generated.resources.Res
import dollarbluekmp.feature.calculator.generated.resources.backspace_outlined_big
import dollarbluekmp.feature.calculator.generated.resources.plus_outline
import dollarbluekmp.feature.calculator.generated.resources.save_as_outlined
import dollarbluekmp.feature.calculator.generated.resources.swap_outlined
import org.jetbrains.compose.resources.vectorResource

@Composable
fun CalculatorButton(
    button: CalButton,
    modifier : Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.background(button.color)
    ) {
        when (button.type) {
            ButtonType.NUMBER->
                Text(
                    text = button.character,
                    fontSize = 26.sp,
                    color = whiteOpacity_80,
                    fontWeight = FontWeight.Medium
                )
            ButtonType.CLEAR ->
                Text(
                    text = button.character,
                    fontSize = 24.sp,
                    color = whiteOpacity_80,
                    fontWeight = FontWeight.Medium
                )
            ButtonType.DELETE ->
                Icon(
                    imageVector = vectorResource(resource = Res.drawable.backspace_outlined_big),
                    contentDescription = "",
                    tint = whiteOpacity_80,
                    modifier = Modifier.size(32.dp)
                )
            ButtonType.OPERATOR ->
                Icon(
                    imageVector = vectorResource(resource = Res.drawable.plus_outline),
                    contentDescription = "",
                    tint = whiteOpacity_80,
                    modifier = Modifier.size(32.dp)
                )
            ButtonType.SAVE ->
                Icon(
                    imageVector = vectorResource(resource = Res.drawable.save_as_outlined),
                    contentDescription = "",
                    tint = whiteOpacity_80,
                    modifier = Modifier.size(32.dp)
                )
            ButtonType.SWITCH ->
                Icon(
                    imageVector = vectorResource(resource = Res.drawable.swap_outlined),
                    contentDescription = "Swap",
                    tint = whiteOpacity_80,
                    modifier = Modifier
                        .size(32.dp)
                        .rotate(90f)
                )
        }
    }
}