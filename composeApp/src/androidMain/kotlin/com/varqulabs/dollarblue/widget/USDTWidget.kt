package com.varqulabs.dollarblue.widget

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.text.FontStyle
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.varqulabs.core.common.extensions.formatDateCustom
import com.varqulabs.core.common.extensions.formatWithDecimals
import com.varqulabs.core.common.extensions.roundDecimals
import com.varqulabs.core.common.time.DatePattern
import com.varqulabs.core.designsystem.greenDark_75
import com.varqulabs.core.designsystem.whiteOpacity_80
import com.varqulabs.dollarblue.MainActivity
import com.varqulabs.dollarblue.R
import com.varqulabs.feature.calculator.data.local.CalculatorPreferenceKey.BOB_VALUE
import com.varqulabs.feature.calculator.data.local.CalculatorPreferenceKey.LAST_UPDATE

class USDTWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceState

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {

            val preferences = currentState<Preferences>()
            val valueSell  = preferences[BOB_VALUE] ?: 0.0
            val valueBuy   = (valueSell * 0.995).roundDecimals(2)
            val date  = preferences[LAST_UPDATE] ?: "–"

            Row(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(greenDark_75)
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                BinanceP2PValues(
                    modifier = GlanceModifier
                        .defaultWeight()
                        .padding(start = 12.dp),
                    valueSell = valueSell,
                    valueBuy = valueBuy,
                    date = date,
                )

                Spacer(modifier = GlanceModifier.width(8.dp))

                DolarIcon(
                    modifier = GlanceModifier.padding(end = 12.dp),
                    onClick = actionStartActivity(
                        Intent(context, MainActivity::class.java)
                            .apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
                    )
                )
            }
        }
    }
}

@Composable
private fun BinanceP2PValues(
    valueSell: Double,
    valueBuy: Double,
    date: String,
    modifier: GlanceModifier = GlanceModifier,
) {
    Column(modifier = modifier) {

        Text(
            text = "USDT Binance P2P · ${date.formatDateCustom(outputPattern = DatePattern.HOUR_DAY_OF_MONTH.pattern)}",
            maxLines = 1,
            style = TextStyle(
                color = ColorProvider(whiteOpacity_80),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )
        )

        Spacer(modifier = GlanceModifier.height(4.dp))

        Text(
            text = "VENTA:  ${valueSell.formatWithDecimals(2)}  Bs",
            style = TextStyle(
                color = ColorProvider(whiteOpacity_80),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
            )
        )

        Text(
            text = "COMPRA:  ${valueBuy.formatWithDecimals(2)}  Bs",
            style = TextStyle(
                color = ColorProvider(whiteOpacity_80),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        )
    }
}

@Composable
private fun DolarIcon(
    modifier: GlanceModifier = GlanceModifier,
    onClick: Action,
) {
    Box(modifier = modifier) {
        Image(
            provider = ImageProvider(R.drawable.dolar_usdt_bolivia),
            contentDescription = "Abrir calculadora",
            modifier = GlanceModifier
                .size(56.dp)
                .clickable(onClick),
        )
    }
}