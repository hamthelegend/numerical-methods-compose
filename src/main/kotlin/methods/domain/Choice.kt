package methods.domain

data class Choice<T>(val value: T, val string: String = value.toString())