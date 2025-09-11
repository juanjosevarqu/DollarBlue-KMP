package com.varqulabs.feature.calculator.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varqulabs.core.designsystem.white_96

@Composable
fun SaveConversionScreen(
    onSaveConversion: (String) -> Unit,
) {

    var conversionName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = white_96,
                shape = RoundedCornerShape(24.dp),
            )
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = conversionName,
            onValueChange = {
                if (it.length <= 36) {
                    conversionName = it
                }
            },
            singleLine = true,
            maxLines = 1,
            label = {
                Text(
                    text = "Detalle de la conversion",
                    style = TextStyle(
                        fontSize = 13.sp
                    ),
                )
            },
            placeholder = {
                Text(
                    text = "Nombre",
                    style = TextStyle(
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.outline,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
            },
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            ),
            shape = MaterialTheme.shapes.large,
            colors = OutlinedTextFieldDefaults.colors(
                focusedLabelColor = Color(0xFF005446),
                cursorColor = Color(0xFF005446),
                focusedBorderColor = Color(0xFF005446),
            )
        )

        Button(
            onClick = { onSaveConversion(conversionName) },
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF004D40)
            )
        ) {
            Text(
                text = if (conversionName.isNotEmpty()) "Guardar" else "Guardar sin Nombre",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
            )
        }
    }
}