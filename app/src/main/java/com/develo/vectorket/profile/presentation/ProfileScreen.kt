package com.develo.vectorket.profile.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.develo.vectorket.auth.domain.model.User
import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.core.presentation.LoadingPlaceholder
import com.develo.vectorket.core.presentation.VectorGrid
import com.develo.vectorket.core.presentation.util.isLoading
import com.develo.vectorket.profile.presentation.ProfileEvent.LogOut
import com.develo.vectorket.profile.presentation.component.ProfileTopBar
import com.develo.vectorket.profile.presentation.component.UserContent

@Composable
fun ProfileScreen(
    user: User,
    favorites: LazyPagingItems<Vector>,
    onFavoriteVectorClick: (Int) -> Unit,
    onEvent: (ProfileEvent) -> Unit
) {
    val gridState = rememberLazyGridState()

    Scaffold(
        topBar = { ProfileTopBar(onLogOutClick = { onEvent(LogOut) }) }
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddings.calculateTopPadding())
        ) {
            UserContent(user = user)
            Text(
                text = "Your Favorites",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            if (favorites.isLoading) {
                LoadingPlaceholder()
            } else if (favorites.itemCount == 0) {
                Text(
                    text = "Looking around and give some likes!",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                VectorGrid(
                    state = gridState,
                    items = favorites,
                    onClick = { index -> onFavoriteVectorClick(index) }
                )
            }
        }
    }
}