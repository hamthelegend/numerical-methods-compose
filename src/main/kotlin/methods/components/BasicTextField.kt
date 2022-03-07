package methods.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun BasicTextField(
    value: String,
    onValueChange: (newValue: String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isNumberOnly: Boolean = false,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        modifier = modifier,
        keyboardOptions =
        KeyboardOptions.Default.copy(keyboardType = if (isNumberOnly) KeyboardType.Number else KeyboardType.Text)
    )
}