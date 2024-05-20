package com.develo.vectorket.core.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.develo.vectorket.core.domain.model.Vector

@Composable
fun VectorGrid(
    modifier: Modifier = Modifier,
    state: LazyGridState,
    items: LazyPagingItems<Vector>,
    onClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        state = state,
        columns = GridCells.Adaptive(120.dp),
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(items.itemCount) { index ->
            items[index]?.let { vector ->
                key(vector.uid) {
                    VectorGridCard(
                        modifier = Modifier.size(120.dp, 150.dp),
                        vector = vector,
                        size = 75.dp,
                        onClick = { onClick(index) }
                    )
                }
            }
        }
    }
}