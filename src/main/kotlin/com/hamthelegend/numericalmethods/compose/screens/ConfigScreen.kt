package com.hamthelegend.numericalmethods.compose.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hamthelegend.numericalmethods.compose.components.BasicTextButton
import com.hamthelegend.numericalmethods.compose.components.BasicTextField
import com.hamthelegend.numericalmethods.compose.components.ExposedDropdownMenu
import com.hamthelegend.numericalmethods.compose.domain.Choice
import com.hamthelegend.numericalmethods.compose.domain.Method
import com.hamthelegend.numericalmethods.compose.domain.solveRoot
import com.hamthelegend.numericalmethods.compose.extensions.equalsOneOf
import methods.common.Fx
import methods.common.IterationResult
import java.math.RoundingMode

@Composable
fun ConfigScreen(
    methodChoices: List<Choice<Method>>,
    methodChoice: Choice<Method>,
    onMethodChoiceChange: (newMethodChoice: Choice<Method>) -> Unit,
    f: String,
    onFChange: (newF: String) -> Unit,
    xL: String,
    onXLChange: (newXL: String) -> Unit,
    xR: String,
    onXRChange: (newXR: String) -> Unit,
    g: String,
    onGChange: (newG: String) -> Unit,
    fPrime: String,
    onFPrimeChange: (newFPrime: String) -> Unit,
    initialX: String,
    onInitialXChange: (newInitialX: String) -> Unit,
    initialXA: String,
    onInitialXAChange: (newInitialXA: String) -> Unit,
    initialXB: String,
    onInitialXBChange: (newInitialXB: String) -> Unit,
    minIterations: String,
    onMinIterationsChange: (newMinIterations: String) -> Unit,
    maxIterations: String,
    onMaxIterationsChange: (newMaxIterations: String) -> Unit,
    scale: String,
    onScaleChange: (newScale: String) -> Unit,
    roundingModeChoices: List<Choice<RoundingMode>>,
    roundingModeChoice: Choice<RoundingMode>,
    onRoundingModeChoiceChange: (newRoundingModeChoice: Choice<RoundingMode>) -> Unit,
    onResultChange: (newResult: IterationResult?) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
    ) {

        ExposedDropdownMenu(
            label = "Method",
            choices = methodChoices,
            choice = methodChoice,
            onChoiceChange = onMethodChoiceChange,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        AnimatedVisibility(
            visible = methodChoice.value.equalsOneOf(Method.BISECTION,
                Method.FALSE_POSITION,
                Method.NEWTON_RAPHSON,
                Method.SECANT)
        ) {
            BasicTextField(
                value = f,
                onValueChange = onFChange,
                label = "f(x)",
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            )
        }

        AnimatedVisibility(visible = methodChoice.value.equalsOneOf(Method.BISECTION, Method.FALSE_POSITION)) {
            BasicTextField(
                value = xL,
                onValueChange = onXLChange,
                label = "xL",
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            )
        }

        AnimatedVisibility(visible = methodChoice.value.equalsOneOf(Method.BISECTION, Method.FALSE_POSITION)) {
            BasicTextField(
                value = xR,
                onValueChange = onXRChange,
                label = "xR",
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            )
        }

        AnimatedVisibility(visible = methodChoice.value == Method.FIXED_POINT) {
            BasicTextField(
                value = g,
                onValueChange = onGChange,
                label = "g(x)",
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            )
        }

        AnimatedVisibility(visible = methodChoice.value == Method.NEWTON_RAPHSON) {
            BasicTextField(
                value = fPrime,
                onValueChange = onFPrimeChange,
                label = "f'(x)",
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            )
        }

        AnimatedVisibility(visible = methodChoice.value.equalsOneOf(Method.FIXED_POINT,
            Method.NEWTON_RAPHSON)) {
            BasicTextField(
                value = initialX,
                onValueChange = onInitialXChange,
                label = "Initial x",
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            )
        }

        AnimatedVisibility(visible = methodChoice.value == Method.SECANT) {
            BasicTextField(
                value = initialXA,
                onValueChange = onInitialXAChange,
                label = "Initial xA",
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            )
        }

        AnimatedVisibility(visible = methodChoice.value == Method.SECANT) {
            BasicTextField(
                value = initialXB,
                onValueChange = onInitialXBChange,
                label = "Initial xB",
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            )
        }

        BasicTextField(
            value = minIterations,
            onValueChange = onMinIterationsChange,
            label = "Minimum iterations",
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        )

        BasicTextField(
            value = maxIterations,
            onValueChange = onMaxIterationsChange,
            label = "Maximum iterations",
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        )

        BasicTextField(
            value = scale,
            onValueChange = onScaleChange,
            label = "Scale",
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        )

        ExposedDropdownMenu(
            label = "Rounding Mode",
            choices = roundingModeChoices,
            choice = roundingModeChoice,
            onChoiceChange = onRoundingModeChoiceChange,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        BasicTextButton(
            text = "Solve root",
            onClick = {
                onResultChange(
                    try {
                        solveRoot(
                            method = methodChoice.value,
                            f = Fx(f),
                            xL = xL.toBigDecimal(),
                            xR = xR.toBigDecimal(),
                            g = Fx(g),
                            fPrime = Fx(fPrime),
                            initialX = initialX.toBigDecimal(),
                            initialXA = initialXA.toBigDecimal(),
                            initialXB = initialXB.toBigDecimal(),
                            minIterations = minIterations.toInt(),
                            maxIterations = maxIterations.toInt(),
                            scale = scale.toInt(),
                            roundingMode = roundingModeChoice.value
                        )
                    } catch (e: Exception) {
                        println(e)
                        null
                    }
                )
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        )

    }

}