package com.hamthelegend.numericalmethods.compose.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BasicTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        modifier = modifier,
    ) {
        Text(text = text)
    }
}