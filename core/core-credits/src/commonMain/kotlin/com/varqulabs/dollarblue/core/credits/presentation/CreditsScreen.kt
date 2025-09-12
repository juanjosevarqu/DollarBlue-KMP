package com.varqulabs.dollarblue.core.credits.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varqulabs.core.designsystem.white_96
import com.varqulabs.dollarblue.core.credits.presentation.CreditsEvent.OnSeeAd

@Composable
fun CreditsScreen(
    hasCredits: Boolean,
    eventHandler: (CreditsEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = white_96,
                shape = RoundedCornerShape(24.dp),
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (!hasCredits) {
            Text(
                text = "¡Ups! Parece que no te quedan créditos.",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color(0xFF004D40),
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {

            Text(
                "Los créditos sirven para:",
                fontStyle = FontStyle.Normal,
                color = Color(0xFF004D40),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )

            Text(
                "- Guardar tus conversiones favoritas",
                fontStyle = FontStyle.Italic,
                color = Color(0xFF00897B),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )

            Spacer(Modifier.height(4.dp))

            Text(
                "Puedes obtenerlos GRATIS viendo anuncios:",
                fontStyle = FontStyle.Normal,
                color = Color(0xFF004D40),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )

            Text(
                "1 Anuncio = 1 Crédito",
                fontStyle = FontStyle.Italic,
                color = Color(0xFF00897B),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Button(
                onClick = { eventHandler(OnSeeAd) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF004D40)
                )
            ) {
                Text(
                    text = "Ver Anuncio",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}