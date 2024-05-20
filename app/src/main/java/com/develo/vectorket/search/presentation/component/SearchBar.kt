package com.develo.vectorket.search.presentation.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.develo.vectorket.core.presentation.LoadingIndicator
import com.develo.vectorket.core.presentation.util.ClearIcon
import com.develo.vectorket.core.presentation.util.SearchIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onClear: () -> Unit,
    onSearch: (String) -> Unit,
    placeholder: String = "",
    isLoading: Boolean = false,
    searchHistories: List<String>
) {
    var active by rememberSaveable {
        mutableStateOf(false)
    }

    val padding: Dp by animateDpAsState(
        targetValue = if (active) 0.dp else 8.dp,
        animationSpec = tween(300),
        label = "searchBarPadding"
    )

    val histories = if (query.isNotBlank())
        searchHistories.filter { it.contains(query, true) }
    else searchHistories

    SearchBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = padding),
        query = query,
        onQueryChange = onQueryChange,
        active = active,
        onActiveChange = { active = it },
        onSearch = {
            if (query.isNotBlank()) {
                active = false
                onSearch(it)
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        leadingIcon = { SearchIcon() },
        trailingIcon = {
            if (active && query.isNotEmpty()) {
                ClearIcon(Modifier.clickable {
                    onClear()
                })
            } else if (isLoading) {
                LoadingIndicator()
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(histories) {
                SearchHistoryItem(
                    text = it,
                    onClick = {
                        active = false
                        onQueryChange(it)
                        onSearch(it)
                    },
                    onOutwardClick = {
                        onQueryChange(it)
                    }
                )
            }
        }
    }
}