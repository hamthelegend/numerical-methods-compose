package com.hamthelegend.numericalmethods.compose.extensions

fun String.toTitleCase(wordSeparator: String): String {
    val stringBuilder = StringBuilder()
    val words = lowercase().split(wordSeparator)
    for (word in words) {
        word.replaceFirstChar { it.uppercase() }
    }
    return stringBuilder.toString()
}