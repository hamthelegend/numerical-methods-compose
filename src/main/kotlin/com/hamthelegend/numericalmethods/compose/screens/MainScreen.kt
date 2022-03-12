package com.hamthelegend.numericalmethods.compose.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import methods.common.Default
import com.hamthelegend.numericalmethods.compose.components.BasicTextField
import com.hamthelegend.numericalmethods.compose.components.DataTable
import com.hamthelegend.numericalmethods.compose.components.ExposedDropdownMenu
import com.hamthelegend.numericalmethods.compose.domain.*
import com.hamthelegend.numericalmethods.compose.domain.Method.*
import com.hamthelegend.numericalmethods.compose.extensions.equalsOneOf
import com.hamthelegend.numericalmethods.compose.extensions.toTitleCase
import methods.common.Fx
import methods.common.IterationResult
import java.math.RoundingMode

@Composable
fun MainScreen() {

    val methodChoices = Method.values().toChoices { toString().toTitleCase("_") }
    var selectedMethodChoice by rememberSaveable { mutableStateOf(methodChoices.first()) }

    var f by rememberSaveable { mutableStateOf("") }
    var xL by rememberSaveable { mutableStateOf("0") }
    var xR by rememberSaveable { mutableStateOf("1") }
    var g by rememberSaveable { mutableStateOf("") }
    var fPrime by rememberSaveable { mutableStateOf("") }
    var initialX by rememberSaveable { mutableStateOf("0") }
    var initialXA by rememberSaveable { mutableStateOf("0") }
    var initialXB by rememberSaveable { mutableStateOf("1") }
    var minIterations by rememberSaveable { mutableStateOf(Default.MIN_ITERATION.toString()) }
    var maxIterations by rememberSaveable { mutableStateOf(Default.MAX_ITERATION.toString()) }
    var scale by rememberSaveable { mutableStateOf(Default.SCALE.toString()) }
    val scrollState = rememberScrollState(initial = 0)

    val roundingModeChoices = RoundingMode.values().toChoices { toString().toTitleCase("_") }
    var selectedRoundingModeChoice by rememberSaveable {
        mutableStateOf(roundingModeChoices.find { it.value == RoundingMode.HALF_UP }!!)
    }

    var result: IterationResult? by rememberSaveable { mutableStateOf(null) }

    Row {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            ExposedDropdownMenu(
                label = "Method",
                choices = methodChoices,
                selectedChoice = selectedMethodChoice,
                onSelectedChoiceChange = { newSelectedChoice -> selectedMethodChoice = newSelectedChoice },
                modifier = Modifier.fillMaxWidth()
            )
            if (selectedMethodChoice.value.equalsOneOf(BISECTION, FALSE_POSITION, NEWTON_RAPHSON, SECANT)) {
                BasicTextField(
                    value = f,
                    onValueChange = { newF -> f = newF },
                    label = "f(x)",
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                )
            }
            if (selectedMethodChoice.value.equalsOneOf(BISECTION, FALSE_POSITION)) {
                BasicTextField(
                    value = xL,
                    onValueChange = { newXL -> xL = newXL },
                    label = "xL",
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                )
                BasicTextField(
                    value = xR,
                    onValueChange = { newXR -> xR = newXR },
                    label = "xR",
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                )
            }
            if (selectedMethodChoice.value == FIXED_POINT) {
                BasicTextField(
                    value = g,
                    onValueChange = { newG -> g = newG },
                    label = "g(x)",
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                )
            }
            if (selectedMethodChoice.value == NEWTON_RAPHSON) {
                BasicTextField(
                    value = fPrime,
                    onValueChange = { newFPrime -> fPrime = newFPrime },
                    label = "f'(x)",
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                )
                BasicTextField(
                    value = initialX,
                    onValueChange = { newInitialX -> initialX = newInitialX },
                    label = "Initial x",
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                )
            }
            if (selectedMethodChoice.value == SECANT) {
                BasicTextField(
                    value = initialXA,
                    onValueChange = { newInitialXA -> initialXA = newInitialXA },
                    label = "Initial xA",
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                )
                BasicTextField(
                    value = initialXB,
                    onValueChange = { newInitialXB -> initialXB = newInitialXB },
                    label = "Initial xB",
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                )
            }
            BasicTextField(
                value = minIterations,
                onValueChange = { newMinIterations -> minIterations = newMinIterations },
                label = "Minimum iterations",
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            )
            BasicTextField(
                value = maxIterations,
                onValueChange = { newMaxIterations -> maxIterations = newMaxIterations },
                label = "Maximum iterations",
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            )
            BasicTextField(
                value = scale,
                onValueChange = { newScale -> scale = newScale },
                label = "Scale",
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            )
            ExposedDropdownMenu(
                label = "Rounding Mode",
                choices = roundingModeChoices,
                selectedChoice = selectedRoundingModeChoice,
                onSelectedChoiceChange = { newSelectedChoice -> selectedRoundingModeChoice = newSelectedChoice }
            )

            Button(
                onClick = {
                    result = try {
                        solveRoot(
                            method = selectedMethodChoice.value,
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
                            roundingMode = selectedRoundingModeChoice.value
                        )
                    } catch (e: Exception) {
                        null
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                )
            ) {
                Text("Solve root")
            }

        }
        result?.let { result ->
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(2f).padding(16.dp)
            ) {
                DataTable(
                    headerValues = result.columnNamesCsv.values,
                    values = result.iterations.mapIndexed { index, iteration ->
                        val valueStrings = iteration.valuesCsv.values.map { value ->
                            value.toString()
                        }.toMutableList()
                        valueStrings.add(0, index.toString())
                        valueStrings
                    },
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        result.tableString.copyToClipboard()
                    },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                    )
                ) {
                    Text("Copy table")
                }
            }
        }
    }

}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}