package com.develo.vectorket.search.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.core.presentation.PullToRefreshContainer
import com.develo.vectorket.core.presentation.VectorGrid
import com.develo.vectorket.core.presentation.util.isLoading
import com.develo.vectorket.search.presentation.component.NoResultFound
import com.develo.vectorket.search.presentation.component.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    result: LazyPagingItems<Vector>,
    searchHistories: List<String>,
    index: Int,
    onSearch: (String) -> Unit,
    onClick: (index: Int) -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()
    val gridState = rememberLazyGridState()

    var searchText by rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(index) {
        gridState.scrollToItem(index)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchBar(
                query = searchText,
                onQueryChange = { searchText = it },
                onSearch = { onSearch(it) },
                onClear = { searchText = "" },
                placeholder = "Search",
                isLoading = result.isLoading && !pullToRefreshState.isRefreshing,
                searchHistories = searchHistories
            )
        }
    ) { paddings ->
        PullToRefreshContainer(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddings.calculateTopPadding()),
            state = pullToRefreshState,
            isLoading = result.isLoading,
            onRefresh = { result.refresh() }
        ) {
            if (searchText.isNotBlank() && result.itemCount == 0 && !result.isLoading) {
                NoResultFound(
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            } else {
                VectorGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp),
                    state = gridState,
                    items = result,
                    onClick = { index -> onClick(index) }
                )
            }
        }
    }
}