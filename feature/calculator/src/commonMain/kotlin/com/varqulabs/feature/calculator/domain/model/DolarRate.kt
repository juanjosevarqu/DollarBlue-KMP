package com.varqulabs.feature.calculator.domain.model

data class DolarRate(
    val type: DollarType = DollarType.OFFICIAL,
    val value: Double = 0.0,
)