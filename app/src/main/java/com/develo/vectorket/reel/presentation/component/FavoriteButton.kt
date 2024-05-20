package com.develo.vectorket.reel.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.develo.vectorket.core.presentation.util.FavoriteBorderIcon
import com.develo.vectorket.core.presentation.util.FavoriteIcon

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
    onFavoriteChange: (Boolean) -> Unit
) {
    Box(
        modifier = modifier.clickable { onFavoriteChange(!isFavorite) },
    ) {
        if (isFavorite) FavoriteIcon(tint = MaterialTheme.colorScheme.primary)
        else FavoriteBorderIcon()
    }
}