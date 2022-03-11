package com.hamthelegend.numericalmethods.compose.domain

data class Choice<T>(val value: T, val string: String = value.toString())