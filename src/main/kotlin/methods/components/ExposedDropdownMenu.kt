package methods.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import java.math.RoundingMode

data class ExposedDropdownMenuItem<T>(val value: T, val string: String)

@Preview
@Composable
fun ExposedDropdownMenuPreview() {
    val choices = RoundingMode.values().map { roundingMode ->
        ExposedDropdownMenuItem(roundingMode, roundingMode.toString())
    }
    var selectedChoice by remember { mutableStateOf(choices.first()) }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        ExposedDropdownMenu(
            choices,
            selectedChoice,
            onSelectedChoiceChange = { newSelectedChoice -> selectedChoice = newSelectedChoice },
            expanded = expanded,
            onExpandChange = { newExpanded -> expanded = newExpanded },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Choice: ${selectedChoice.string}")
    }
}

@Composable
fun <T> ExposedDropdownMenu(
    choices: List<ExposedDropdownMenuItem<T>>,
    selectedChoice: ExposedDropdownMenuItem<T>,
    onSelectedChoiceChange: (newSelectedChoice: ExposedDropdownMenuItem<T>) -> Unit,
    expanded: Boolean,
    onExpandChange: (newExpanded: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier) {
        TextField(
            readOnly = true,
            value = selectedChoice.string,
            onValueChange = {  },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text("Label") },
            trailingIcon = {
                IconButton(onClick = { onExpandChange(!expanded) }) {
                    Icon(imageVector = icon, if (expanded) "Close Dropdown" else "Expand Dropdown")
                }
            },
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandChange(false) },
            modifier = Modifier.width(with(LocalDensity.current){ textFieldSize.width.toDp() })
        ) {
            choices.forEach { choice ->
                DropdownMenuItem(onClick = {
                    onSelectedChoiceChange(choice)
                    onExpandChange(false)
                }) {
                    Text(text = choice.string)
                }
            }
        }
    }
}