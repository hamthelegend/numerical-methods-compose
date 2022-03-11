package com.hamthelegend.numericalmethods.compose.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun DataTablePreview() {
    DataTable(
        headerValues = ('A'..'J').map { it.toString() },
        values = (Array(200) { (1..10).map { it.toString() } }).toList(),
//        backgroundColor = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(16.dp).fillMaxSize()
    )
}

@Composable
fun DataTable(
    headerValues: List<String>,
    values: List<List<String>>,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.background,
    textAlign: TextAlign? = null,
) {
    val lazyColumnState = rememberLazyListState()
    val headerElevation by animateDpAsState(
        if (lazyColumnState.firstVisibleItemIndex == 0 && lazyColumnState.firstVisibleItemScrollOffset == 0) 0.dp else 4.dp
    )

    Card(backgroundColor = backgroundColor, modifier = modifier) {
        Column {
            Surface(elevation = headerElevation) {
                DataTableRow(
                    values = headerValues,
                    textAlign = textAlign,
                    header = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize(), lazyColumnState) {
                items(values) { rowValues ->
                    DataTableRow(
                        values = rowValues,
                        textAlign = textAlign,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun DataTableRow(
    values: List<String>,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    header: Boolean = false,
) {
    Row(modifier = modifier) {
        for (cellValue in values) {
            Column(modifier = Modifier.weight(1f)) {
                DataTableCell(
                    value = cellValue,
                    textAlign = textAlign,
                    header = header,
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
    header: Boolean = false,
) {
    Text(
        text = value,
        textAlign = textAlign,
        fontWeight = if (header) FontWeight.Bold else null,
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}