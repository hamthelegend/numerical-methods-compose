package com.hamthelegend.numericalmethods.compose.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hamthelegend.numericalmethods.compose.domain.Method
import com.hamthelegend.numericalmethods.compose.domain.toChoices
import com.hamthelegend.numericalmethods.compose.extensions.toTitleCase
import methods.common.Default
import methods.common.IterationResult
import java.math.RoundingMode

@Composable
fun MainScreen() {

    val methodChoices = Method.values().toChoices { toString().toTitleCase("_") }
    var methodChoice by rememberSaveable { mutableStateOf(methodChoices.first()) }

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
    var roundingModeChoice by rememberSaveable {
        mutableStateOf(roundingModeChoices.find { it.value == RoundingMode.HALF_UP }!!)
    }

    var result: IterationResult? by rememberSaveable { mutableStateOf(null) }

    Row {
        Scaffold(
            topBar = { TopBar() }
        ) {
            ConfigScreen(
                methodChoices = methodChoices,
                methodChoice = methodChoice,
                onMethodChoiceChange = { newMethodChoice -> methodChoice = newMethodChoice },
                f = f,
                onFChange = { newF -> f = newF },
                xL = xL,
                onXLChange = { newXL -> xL = newXL },
                xR = xR,
                onXRChange = { newXR -> xR = newXR },
                g = g,
                onGChange = { newG -> g = newG },
                fPrime = fPrime,
                onFPrimeChange = { newFPrime -> fPrime = newFPrime },
                initialX = initialX,
                onInitialXChange = { newInitialX -> initialX = newInitialX },
                initialXA = initialXA,
                onInitialXAChange = { newInitialXA -> initialXA = newInitialXA },
                initialXB = initialXB,
                onInitialXBChange = { newInitialXB -> initialXB = newInitialXB },
                minIterations = minIterations,
                onMinIterationsChange = { newMinIterations -> minIterations = newMinIterations },
                maxIterations = maxIterations,
                onMaxIterationsChange = { newMaxIterations -> maxIterations = newMaxIterations },
                scale = scale,
                onScaleChange = { newScale -> scale = newScale },
                roundingModeChoices = roundingModeChoices,
                roundingModeChoice = roundingModeChoice,
                onRoundingModeChoiceChange = { newRoundingModeChoice -> roundingModeChoice = newRoundingModeChoice },
                onResultChange = { newResult -> result = newResult },
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            )
            ResultScreen(
                result = result,
                modifier = Modifier.fillMaxWidth(fraction = 2f / 3).padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun TopBar() {
    TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Numerical Methods",
            style = MaterialTheme.typography.h6
        )
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}