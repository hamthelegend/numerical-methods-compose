package com.hamthelegend.numericalmethods.compose.domain

data class Choice<T>(val value: T, val choiceToString: (T.() -> String) = { toString() }) {
    override fun toString() = value.choiceToString()
}

fun <T> T.toChoice(choiceToString: T.() -> String = { toString() }) = Choice(this, choiceToString)
fun <T> Array<T>.toChoices(choiceToString: T.() -> String = { toString() }) = map { Choice(it, choiceToString) }
fun <T> Collection<T>.toChoices(choiceToString: (T.() -> String) = { toString() }) = map { Choice(it, choiceToString) }