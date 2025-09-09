package com.varqulabs.feature.calculator.data.remote.dto

import com.varqulabs.feature.calculator.domain.model.BolivianUSDT
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BolivianUSDTDto(
    @SerialName("venta") val valueSell: Double = 0.0,
    @SerialName("fechaActualizacion") val dateUpdated: String = "",
)

fun BolivianUSDTDto.mapToDomain() = BolivianUSDT(
    valueSell = valueSell,
    dateUpdated = dateUpdated,
)