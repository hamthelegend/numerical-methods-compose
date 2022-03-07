package methods.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import methods.common.Default
import methods.components.BasicTextField
import java.math.RoundingMode

@Composable
fun MainScreen() {
    var f by rememberSaveable { mutableStateOf("") }
    var xL by rememberSaveable { mutableStateOf("0") }
    var xR by rememberSaveable { mutableStateOf("1") }
    var g by rememberSaveable { mutableStateOf("") }
    var fPrime by rememberSaveable { mutableStateOf("") }
    var initialXA by rememberSaveable { mutableStateOf("0") }
    var initialXB by rememberSaveable { mutableStateOf("1") }
    var minIterations by rememberSaveable { mutableStateOf("0") }
    var maxIterations by rememberSaveable { mutableStateOf("5") }
    var scale by rememberSaveable { mutableStateOf(Default.SCALE.toString()) }
    val scrollState = rememberScrollState(initial = 0)

    Row {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            BasicTextField(
                value = f,
                onValueChange = { newF -> f = newF },
                label = "f(x)",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            )
            BasicTextField(
                value = xL.toString(),
                onValueChange = { newXL -> xL = newXL },
                label = "xL",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            )
            BasicTextField(
                value = xR.toString(),
                onValueChange = { newXR -> xR = newXR },
                label = "xR",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            )
            BasicTextField(
                value = g,
                onValueChange = { newG -> g = newG },
                label = "g(x)",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            )
            BasicTextField(
                value = fPrime,
                onValueChange = { newFPrime -> fPrime = newFPrime },
                label = "f'(x)",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            )
            BasicTextField(
                value = initialXA.toString(),
                onValueChange = { newInitialXA -> initialXA = newInitialXA },
                label = "Initial xA",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            )
            BasicTextField(
                value = initialXB.toString(),
                onValueChange = { newInitialXB -> initialXB = newInitialXB },
                label = "Initial xB",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            )
            BasicTextField(
                value = minIterations.toString(),
                onValueChange = { newMinIterations -> minIterations = newMinIterations },
                label = "Minimum iterations",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            )
            BasicTextField(
                value = maxIterations.toString(),
                onValueChange = { newMaxIterations -> maxIterations = newMaxIterations },
                label = "Maximum iterations",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            )
            BasicTextField(
                value = scale.toString(),
                onValueChange = { newScale -> scale = newScale },
                label = "Scale",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            )
        }
        Column {
            
        }
    }

}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}