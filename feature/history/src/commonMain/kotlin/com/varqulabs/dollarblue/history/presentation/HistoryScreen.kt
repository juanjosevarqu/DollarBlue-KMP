package com.varqulabs.dollarblue.history.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.varqulabs.core.designsystem.whiteCeCe
import com.varqulabs.core.designsystem.white_96
import com.varqulabs.dollarblue.core.conversions.domain.model.CurrencyConversion
import com.varqulabs.dollarblue.history.presentation.HistoryEvent.OnDeleteConversion
import com.varqulabs.dollarblue.history.presentation.components.CurrencyConversionCard
import com.varqulabs.dollarblue.history.presentation.components.CurrentCredits
import com.varqulabs.dollarblue.history.presentation.components.EmptyStateCurrencyConversions
import dollarbluekmp.feature.history.generated.resources.Res
import dollarbluekmp.feature.history.generated.resources.calculator
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    state: HistoryState,
    eventHandler: (HistoryEvent) -> Unit,
    goToCalculator: () -> Unit,
    showCreditsInfo: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CurrentCredits(
                        credits = state.credits,
                        onClickCredits = showCreditsInfo,
                    )
                },
                actions = {
                    IconButton(onClick = goToCalculator) {
                        Icon(
                            imageVector = vectorResource(resource = Res.drawable.calculator),
                            contentDescription = "Calculator Button",
                            tint = Color.DarkGray,
                            modifier = Modifier.size(28.dp),
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = whiteCeCe
                )
            )
        },
        containerColor = white_96
    ) { paddingValues ->

        if (state.currencyConversions.isNotEmpty()) {
            HistoryReceiptGrid(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                conversions = state.currencyConversions,
                onDelete = { eventHandler(OnDeleteConversion(it)) }
            )
        } else {
            EmptyStateCurrencyConversions(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth(),
                goToCalculator = goToCalculator
            )
        }

    }
}

@Composable
private fun HistoryReceiptGrid(
    conversions: List<CurrencyConversion>,
    modifier: Modifier = Modifier,
    onDelete: (Long) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalItemSpacing = 12.dp,
    ) {
        items(items = conversions, key = { it.conversionId }) { conversion ->
            CurrencyConversionCard(
                modifier = Modifier,
                conversion = conversion,
                onDelete = { onDelete(conversion.localId) }
            )
        }
    }
}