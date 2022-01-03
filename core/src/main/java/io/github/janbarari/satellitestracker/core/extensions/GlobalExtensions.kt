package io.github.janbarari.satellitestracker.core.extensions

import java.text.DecimalFormat

fun String.currencyFormat(): String? {
    kotlin.runCatching {
        val m = this.toDouble()
        val formatter = DecimalFormat("###,###,###")
        return formatter.format(m)
    }
    return this
}