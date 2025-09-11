package com.varqulabs.feature.calculator.presentation.models

import androidx.compose.ui.graphics.Color
import com.varqulabs.core.designsystem.greenDark_75
import com.varqulabs.core.designsystem.greenDark_80

enum class CalButton(
    val character: String,
    val type: ButtonType,
    val color: Color,
) {
    ONE("1", ButtonType.NUMBER, greenDark_75),
    TWO("2", ButtonType.NUMBER, greenDark_75),
    THREE("3", ButtonType.NUMBER, greenDark_75),
    DELETE("D", ButtonType.DELETE, greenDark_80),

    FOUR("4", ButtonType.NUMBER, greenDark_75),
    FIVE("5", ButtonType.NUMBER, greenDark_75),
    SIX("6", ButtonType.NUMBER, greenDark_75),
    CLEAR("AC", ButtonType.CLEAR, greenDark_80),

    SEVEN("7", ButtonType.NUMBER, greenDark_75),
    EIGHT("8", ButtonType.NUMBER, greenDark_75),
    NINE("9", ButtonType.NUMBER, greenDark_75),
    PLUS("+", ButtonType.OPERATOR, greenDark_80),

    DOUBLE_ZERO("00", ButtonType.NUMBER, greenDark_75),
    ZERO("0", ButtonType.NUMBER, greenDark_75),
    SAVE("S", ButtonType.SAVE, greenDark_80),
    SWITCH("$", ButtonType.SWITCH, greenDark_80);
}