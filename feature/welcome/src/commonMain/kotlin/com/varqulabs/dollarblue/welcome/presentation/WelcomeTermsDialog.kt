package com.varqulabs.dollarblue.welcome.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun WelcomeTermsDialog(
    onAcceptTerms: () -> Unit
) {

    var isAccepted by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = { if (isAccepted) onAcceptTerms() },
    ) {
        Column(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.extraLarge)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {

            WelcomeToDolarBlue(modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray))

            AboutThisApp(modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray))

            Text(
                text = "Nota:",
                color = Color.Red,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
            )

            TermsScrollable(modifier = Modifier.fillMaxWidth())

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = isAccepted,
                    onCheckedChange = { isAccepted = it }
                )
                TextButton(onClick = { isAccepted = !isAccepted }) {
                    Text(
                        text = "Acepto los términos y condiciones",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF004D40),
                    )
                }
            }

            Button(
                onClick = onAcceptTerms,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                shape = MaterialTheme.shapes.extraLarge,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF004D40)
                ),
                enabled = isAccepted
            ) {
                Text(
                    text = "Entendido",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun WelcomeToDolarBlue(
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            append("Bienvenido a la App: \n")
            withStyle(
                style = SpanStyle(
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color(0xFF00897B)
                )
            ) {
                append("Dolar Blue Bolivia - USDT")
            }

        },
        fontStyle = FontStyle.Normal,
        color = Color(0xFF004D40),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = modifier
    )
}

@Composable
private fun AboutThisApp(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {

        Text(
            text = "Esta App te permite hacer conversiones rapidas entre:",
            fontStyle = FontStyle.Normal,
            color = Color(0xFF004D40),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Text(
            "- Dolares y Bolivianos",
            fontStyle = FontStyle.Italic,
            color = Color(0xFF00897B),
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = "Próximamente podras guardar tus conversiones favoritas y ver el historial de conversiones realizadas.",
            fontStyle = FontStyle.Normal,
            color = Color(0xFF004D40),
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun TermsScrollable(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .height(155.dp)
            .verticalScroll(scrollState)
            .drawBehind {
                // Tamaño fijo para la barra de desplazamiento
                val scrollbarHeight = size.height * 0.1f
                val scrollbarPosition =
                    (size.height - scrollbarHeight) * (scrollState.value.toFloat() / scrollState.maxValue)

                if (scrollState.maxValue > 0) {
                    drawRoundRect(
                        color = Color.Gray,
                        topLeft = Offset(size.width - 8.dp.toPx(), scrollbarPosition),
                        size = Size(4.dp.toPx(), scrollbarHeight),
                        cornerRadius = CornerRadius(2.dp.toPx())
                    )
                }
            }
    ) {
        Column {
            Text(
                text = "Dolar Oficial = 6.96 BOB\n\n" +
                    "Dolar Blue Bolivia - USDT es una aplicación informativa que proporciona datos sobre cotizaciones de divisas en Bolivia.\n" +
                        "NO tiene intención de influir en el mercado ni de realizar especulación alguna sobre el precio del dólar en Bolivia.\n\n" +
                        "Los valores mostrados son aproximados y pueden no reflejar las cotizaciones reales o actuales en los mercados correspondientes.\n\n" +
                        "El equipo de Dolar Blue Bolivia - USDT no se responsabiliza por la precisión, veracidad, exactitud, integridad o vigencia de los datos presentados.\n" +
                        "No asumimos responsabilidad alguna por el uso que los usuarios puedan dar a esta información, ni por cualquier daño o perjuicio que pueda derivarse del uso de los datos proporcionados.\n\n" +
                        "Al utilizar Dolar Blue Bolivia - USDT, aceptas estos términos y condiciones.\n" +
                        "Nos reservamos el derecho de modificar, suspender o interrumpir el servicio en cualquier momento sin previo aviso.",
                fontStyle = FontStyle.Italic,
                fontSize = 13.sp,
                color = Color(0xFF004D40),
                fontWeight = FontWeight.Bold,
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0xFF004D40).copy(alpha = 0.1f)
                            )
                        )
                    )
            )
        }
    }
}