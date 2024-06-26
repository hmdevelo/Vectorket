package com.develo.vectorket.reel.presentation.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.develo.vectorket.core.presentation.util.BackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReelTopBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    showNavigateUp: Boolean,
    onNavigateUp: () -> Unit
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (showNavigateUp) {
                IconButton(onClick = onNavigateUp) {
                    BackIcon()
                }
            }
        }
    )
}