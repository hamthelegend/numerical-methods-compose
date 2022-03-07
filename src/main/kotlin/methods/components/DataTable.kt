package methods.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DataTableRow(
    cellValues: List<String>,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
) {
    Surface(
        modifier = modifier,
        color = backgroundColor
    ) {
        SelectionContainer(modifier = Modifier.fillMaxSize()) {
            LazyRow(modifier = Modifier.fillMaxSize()) {
                items(cellValues) { cellValue ->
                    Text(
                        text = cellValue + '\t',
                        modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DataTableRowPreview() {
    DataTableRow(
        cellValues = listOf("a", "b", "c"),
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background
    )
}