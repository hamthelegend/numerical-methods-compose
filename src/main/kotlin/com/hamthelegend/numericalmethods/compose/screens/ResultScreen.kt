package com.hamthelegend.numericalmethods.compose.screens

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hamthelegend.numericalmethods.compose.components.BasicTextButton
import com.hamthelegend.numericalmethods.compose.components.DataTable
import com.hamthelegend.numericalmethods.compose.domain.copyToClipboard
import com.hamthelegend.numericalmethods.compose.domain.tableString
import methods.common.IterationResult

@Composable
fun ResultScreen(
    result: IterationResult?,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current

    AnimatedVisibility(
        visible = result != null,
        enter = slideInHorizontally {
            with(density) { 40.dp.roundToPx() }
        } + fadeIn(initialAlpha = 0.3f) + expandHorizontally(expandFrom = Alignment.End),
        exit = slideOutHorizontally {
            with(density) { 40.dp.roundToPx() }
        } + fadeOut(targetAlpha = 0.3f) + shrinkHorizontally(shrinkTowards = Alignment.End),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier,
        ) {
            DataTable(
                headerValues = result?.columnNamesCsv?.values ?: listOf(),
                values = result?.iterations?.mapIndexed { index, iteration ->
                    val valueStrings = iteration.valuesCsv.values.map { value ->
                        value.toString()
                    }.toMutableList()
                    valueStrings.add(0, index.toString())
                    valueStrings
                } ?: listOf(),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            BasicTextButton(
                text = "Copy table",
                onClick = { result?.tableString?.copyToClipboard() },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}