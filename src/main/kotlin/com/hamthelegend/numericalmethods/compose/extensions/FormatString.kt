package com.hamthelegend.numericalmethods.compose.extensions

fun String.toTitleCase(wordSeparator: String = " ", newWordSeparator: String = " "): String {
    val stringBuilder = StringBuilder()
    val words = lowercase().split(wordSeparator)
    for ((index, word) in words.withIndex()) {
        stringBuilder.append(word.replaceFirstChar { it.uppercase() })
        if (index != words.lastIndex) stringBuilder.append(newWordSeparator)
    }
    return stringBuilder.toString()
}