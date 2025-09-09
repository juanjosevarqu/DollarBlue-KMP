package com.varqulabs.dollarblue.core.conversions.domain.model

import dollarbluekmp.core.core_conversions.generated.resources.Res
import dollarbluekmp.core.core_conversions.generated.resources.boliviano
import dollarbluekmp.core.core_conversions.generated.resources.dollar
import org.jetbrains.compose.resources.DrawableResource

enum class Currency(
    val label: String,
    val symbol: String,
    val code: String,
    val symbolImage: DrawableResource,
) {
    DOLLAR("Dólares", "$", "USD", Res.drawable.dollar),
    BOLIVIANO("Bolivianos", "Bs", "BOB", Res.drawable.boliviano);

    companion object {
        fun fromCode(code: String): Currency =
            entries.firstOrNull { it.code == code } ?: DOLLAR
    }
}