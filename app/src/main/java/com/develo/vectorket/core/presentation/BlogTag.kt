package com.develo.vectorket.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.develo.vectorket.blog.presentation.util.getContainerColor
import com.develo.vectorket.blog.presentation.util.getContentColor

@Composable
fun BlogTag(
    text: String
) {
    val backgroundColor = getContainerColor(text)
    val textColor = getContentColor(text)

    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraSmall)
            .background(backgroundColor)
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = textColor
        )
    }
}