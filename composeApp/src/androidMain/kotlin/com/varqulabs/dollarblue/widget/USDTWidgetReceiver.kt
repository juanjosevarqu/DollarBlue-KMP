package com.varqulabs.dollarblue.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.updateAll
import com.varqulabs.core.common.utils.DataState
import com.varqulabs.feature.calculator.domain.usecase.bolivian_usdt.GetBolivianUSDT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class USDTWidgetReceiver : GlanceAppWidgetReceiver(), KoinComponent {

    override val glanceAppWidget: GlanceAppWidget = USDTWidget()

    private val getBolivianUSDT: GetBolivianUSDT by inject()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        CoroutineScope(Dispatchers.IO).launch {
            getBolivianUSDT()
                .catch {  }
                .collectLatest { state ->
                    if (state is DataState.Success) {
                        USDTWidget().updateAll(context)
                    }
                }
        }
    }
}