package com.develo.vectorket.reel.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.develo.vectorket.core.presentation.util.formatCount

@Composable
fun LikeCount(
    modifier: Modifier = Modifier,
    likeCount: Long,
    isFavorite: Boolean
) {
    val text = if (likeCount == 0L) {
        "Be the first one to like this"
    } else if (likeCount == 1L && isFavorite) {
        "You like this"
    } else if (likeCount == 1L) {
        "1 like"
    } else if (likeCount == 2L && isFavorite) {
        "You and 1 other like this"
    } else if (likeCount < 1000L) {
        "$likeCount likes"
    } else {
        likeCount.formatCount()
    }

    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier
    )
}