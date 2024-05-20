package com.develo.vectorket.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.develo.vectorket.blog.domain.alias.BlogsResponse
import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.core.presentation.PullToRefreshContainer
import com.develo.vectorket.core.presentation.util.data
import com.develo.vectorket.core.presentation.util.isLoading
import com.develo.vectorket.home.domain.alias.LatestVectorResponse
import com.develo.vectorket.home.presentation.component.heroBanner
import com.develo.vectorket.home.presentation.component.latestBlogs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    latestVectorResponse: LatestVectorResponse,
    latestBlogsResponse: BlogsResponse,
    onLatestVectorClick: () -> Unit,
    onBlogClick: (Int) -> Unit,
    onRefresh: () -> Unit
) {
    Scaffold { paddings ->
        PullToRefreshContainer(
            onRefresh = onRefresh,
            isLoading = latestVectorResponse.isLoading
        ) { pullToRefreshState ->

            val lazyListState = rememberLazyListState()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(pullToRefreshState.nestedScrollConnection),
                state = lazyListState
            ) {
                heroBanner(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    state = lazyListState,
                    latestVector = latestVectorResponse.data ?: Vector(),
                    isLoading = latestVectorResponse.isLoading && !pullToRefreshState.isRefreshing,
                    onClick = onLatestVectorClick
                )

                latestBlogs(
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = latestBlogsResponse.isLoading && !pullToRefreshState.isRefreshing,
                    blogs = latestBlogsResponse.data ?: emptyList(),
                    onClick = onBlogClick
                )
            }
        }

        // Status bar placeholder.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(paddings.calculateTopPadding())
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.4f))
        )
    }
}