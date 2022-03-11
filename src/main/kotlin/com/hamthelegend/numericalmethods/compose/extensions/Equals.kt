package com.hamthelegend.numericalmethods.compose.extensions

fun <T> T.equalsOneOf(vararg others: T): Boolean {
    for (other in others) {
        if (this == other) return true
    }
    return false
}

fun <T> T.equalsAllOf(vararg others: T): Boolean {
    for (other in others) {
        if (this != other) return false
    }
    return true
}