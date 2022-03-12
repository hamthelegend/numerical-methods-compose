package com.hamthelegend.numericalmethods.compose.domain

import methods.common.Fx
import methods.common.IterationResult
import methods.iterativebracketing.BracketInterval
import methods.iterativebracketing.runBisection
import methods.iterativebracketing.runFalsePosition
import methods.open.runFixedPoint
import methods.open.runNewtonRaphson
import methods.open.runSecant
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.math.BigDecimal
import java.math.RoundingMode

fun solveRoot(
    method: Method,
    f: Fx,
    xL: BigDecimal,
    xR: BigDecimal,
    g: Fx,
    fPrime: Fx,
    initialX: BigDecimal,
    initialXA: BigDecimal,
    initialXB: BigDecimal,
    minIterations: Int,
    maxIterations: Int,
    scale: Int,
    roundingMode: RoundingMode,
) = when (method) {
    Method.BISECTION -> runBisection(
        f = f,
        initialInterval = BracketInterval(xL, xR),
        minIterations = minIterations,
        maxIterations = maxIterations,
        scale = scale,
        roundingMode = roundingMode,
    )
    Method.FALSE_POSITION -> runFalsePosition(
        f = f,
        initialInterval = BracketInterval(xL, xR),
        minIterations = minIterations,
        maxIterations = maxIterations,
        scale = scale,
        roundingMode = roundingMode,
    )
    Method.FIXED_POINT -> runFixedPoint(
        g = g,
        initialX = initialX,
        minIterations = minIterations,
        maxIterations = maxIterations,
        scale = scale,
        roundingMode = roundingMode
    )
    Method.NEWTON_RAPHSON -> runNewtonRaphson(
        f = f,
        fPrime = fPrime,
        initialX = initialX,
        minIterations = minIterations,
        maxIterations = maxIterations,
        scale = scale,
        roundingMode = roundingMode
    )
    Method.SECANT -> runSecant(
        f = f,
        initialXA = initialXA,
        initialXB = initialXB,
        minIterations = minIterations,
        maxIterations = maxIterations,
        scale = scale,
        roundingMode = roundingMode
    )
}

fun String.copyToClipboard() {
    val stringSelection = StringSelection(this)
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(stringSelection, null)
}

val IterationResult.tableString: String
    get() {
        val stringBuilder = StringBuilder(columnNamesCsv.values.joinToString("\t"))
        stringBuilder.append("\n")
        for ((index, iteration) in iterations.withIndex()) {
            stringBuilder.append("$index\t")
            stringBuilder.append(iteration.valuesCsv.values.joinToString("\t"))
            stringBuilder.append("\n")
        }
        return stringBuilder.toString()
    }