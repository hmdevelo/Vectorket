package com.develo.vectorket.reel.presentation

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.paging.compose.LazyPagingItems
import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.core.presentation.LoadingPlaceholder
import com.develo.vectorket.core.presentation.PullToRefreshContainer
import com.develo.vectorket.core.presentation.util.isLoading
import com.develo.vectorket.reel.domain.model.Favorite
import com.develo.vectorket.reel.presentation.ReelEvent.AddToFavorites
import com.develo.vectorket.reel.presentation.ReelEvent.Download
import com.develo.vectorket.reel.presentation.ReelEvent.RemoveFromFavorites
import com.develo.vectorket.reel.presentation.component.ReelCard
import com.develo.vectorket.reel.presentation.component.ReelTopBar
import com.develo.vectorket.reel.presentation.util.contains

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReelScreen(
    items: LazyPagingItems<Vector>,
    index: Int,
    title: String = "",
    favorites: List<Favorite>,
    showNavigateUp: Boolean = true,
    onEvent: (ReelEvent) -> Unit,
    onIndexChange: (Int) -> Unit,
    onRefresh: () -> Unit,
    onNavigateUp: () -> Unit
) {
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = index
    )

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val context = LocalContext.current

    val handleNavigateUp: () -> Unit = {
        onIndexChange(listState.firstVisibleItemIndex + 1)
        onNavigateUp()
    }

    val handleFavoriteChange: (Boolean, Vector) -> Unit = { checked, vector ->
        onEvent(
            if (checked) AddToFavorites(vector)
            else RemoveFromFavorites(vector)
        )
        items.refresh()
    }

    var isPermissionGranted by rememberSaveable {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PERMISSION_GRANTED
        )
    }

    val writePermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        isPermissionGranted = it
    }

    Scaffold(
        topBar = {
            ReelTopBar(
                title = title,
                scrollBehavior = scrollBehavior,
                showNavigateUp = showNavigateUp,
                onNavigateUp = { handleNavigateUp() })
        }
    ) { paddings ->
        PullToRefreshContainer(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddings.calculateTopPadding()),
            isLoading = items.isLoading,
            onRefresh = { onRefresh() }
        ) { state ->
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items.itemCount) {
                    items[it]?.let { vector ->
                        key(vector.uid) {
                            ReelCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                vector = vector,
                                isFavorite = favorites.contains(vector.uid),
                                onFavoriteChange = { checked ->
                                    handleFavoriteChange(checked, vector)
                                },
                                onDownloadClick = {
                                    if (!isPermissionGranted) {
                                        writePermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    } else {
                                        onEvent(Download(vector))
                                    }
                                }
                            )
                        }
                    }
                }
                if (items.isLoading && !state.isRefreshing) {
                    item {
                        LoadingPlaceholder()
                    }
                }
                item { Spacer(modifier = Modifier.padding(8.dp)) }
            }
        }
    }

    BackHandler(
        enabled = true,
        onBack = { handleNavigateUp() }
    )
}