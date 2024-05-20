package com.develo.vectorket.blog.presentation.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun getContainerColor(tag: String) = when (tag) {
    TAGS_SUGGESTION -> MaterialTheme.colorScheme.surfaceVariant
    TAGS_ALERT -> MaterialTheme.colorScheme.errorContainer
    TAGS_ANNOUNCEMENT -> MaterialTheme.colorScheme.tertiaryContainer
    else -> MaterialTheme.colorScheme.primaryContainer
}

@Composable
fun getContentColor(tag: String) = when (tag) {
    TAGS_SUGGESTION -> MaterialTheme.colorScheme.onSurfaceVariant
    TAGS_ALERT -> MaterialTheme.colorScheme.onErrorContainer
    TAGS_ANNOUNCEMENT -> MaterialTheme.colorScheme.onTertiaryContainer
    else -> MaterialTheme.colorScheme.onPrimaryContainer
}

fun String.asParagraph() = this.replace("_n", "\n")