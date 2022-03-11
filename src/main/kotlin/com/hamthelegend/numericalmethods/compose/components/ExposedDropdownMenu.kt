package com.hamthelegend.numericalmethods.compose.components

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
import com.hamthelegend.numericalmethods.compose.domain.Choice
import java.math.RoundingMode

@Preview
@Composable
fun ExposedDropdownMenuPreview() {
    val choices = RoundingMode.values().map { roundingMode ->
        Choice(roundingMode)
    }
    var selectedChoice by remember { mutableStateOf(choices.first()) }

    Column(modifier = Modifier.padding(16.dp)) {
        ExposedDropdownMenu(
            choices = choices,
            selectedChoice = selectedChoice,
            onSelectedChoiceChange = { newSelectedChoice -> selectedChoice = newSelectedChoice },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Choice: $selectedChoice")
    }
}

@Composable
fun <T> ExposedDropdownMenu(
    choices: List<Choice<T>>,
    selectedChoice: Choice<T>,
    onSelectedChoiceChange: (newSelectedChoice: Choice<T>) -> Unit,
    modifier: Modifier = Modifier,
) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp //it requires androidx.com.hamthelegend.numericalmethods.compose.material:material-icons-extended
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier) {
        TextField(
            readOnly = true,
            value = selectedChoice.toString(),
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text("Label") },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(imageVector = icon, if (expanded) "Close Dropdown" else "Expand Dropdown")
                }
            },
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            choices.forEach { choice ->
                DropdownMenuItem(
                    onClick = {
                        onSelectedChoiceChange(choice)
                        expanded = false
                        println(choice)
                    }
                ) {
                    Text(text = choice.toString())
                }
            }
        }
    }
}