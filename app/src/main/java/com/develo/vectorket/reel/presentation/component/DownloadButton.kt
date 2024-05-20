package com.develo.vectorket.reel.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.develo.vectorket.core.presentation.util.DownloadIcon

@Composable
fun DownloadButton(
    modifier: Modifier = Modifier,
    text: String = "Download",
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        DownloadIcon()
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}