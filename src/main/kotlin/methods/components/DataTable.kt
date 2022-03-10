package methods.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DataTable(
    values: List<List<String>>,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
) {
    Card(backgroundColor = backgroundColor, modifier = modifier) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(values) { index, rowValues ->
                DataTableRow(
                    values = rowValues,
                    textAlign = textAlign,
                    isHeader = index == 0,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun DataTableRow(
    values: List<String>,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    isHeader: Boolean = false,
) {
    Row(modifier = modifier) {
        for (cellValue in values) {
            Column(modifier = Modifier.weight(1f)) {
                DataTableCell(
                    value = cellValue,
                    textAlign = textAlign,
                    isHeader = isHeader,
                )
                Divider(Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun DataTableCell(
    value: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    isHeader: Boolean = false,
) {
    Text(
        text = value,
        textAlign = textAlign,
        fontWeight = if (isHeader) FontWeight.Bold else null,
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxSize(),
    )
}

@Preview
@Composable
fun DataTablePreview() {
    val values = mutableListOf(('A'..'J').map { it.toString() })
    repeat(200) {
        values.add((1..10).map { it.toString() })
    }
    DataTable(
        values = values,
        backgroundColor = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(16.dp).fillMaxSize()
    )
}