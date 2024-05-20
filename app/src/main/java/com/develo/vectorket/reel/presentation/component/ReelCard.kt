package com.develo.vectorket.reel.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.core.presentation.util.getSizeString

@Composable
fun ReelCard(
    modifier: Modifier = Modifier,
    vector: Vector,
    isFavorite: Boolean = false,
    onFavoriteChange: (Boolean) -> Unit,
    onDownloadClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
    ) {
        vector.run {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(16.dp)
            )
            ReelImageHolder(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .height(260.dp),
                vector = vector
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FavoriteButton(
                        isFavorite = isFavorite,
                        onFavoriteChange = { onFavoriteChange(it) }
                    )
                    LikeCount(
                        modifier = Modifier.padding(start = 4.dp),
                        likeCount = likeCount,
                        isFavorite = isFavorite
                    )
                }
                DownloadButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = vector.fileInfo.getSizeString(),
                    onClick = onDownloadClick
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ReelCardPreview() {
    MaterialTheme {
        ReelCard(
            vector = Vector(
                title = "Cinderella drinking beer.",
                likeCount = 10
            ),
            isFavorite = true,
            onFavoriteChange = { },
            onDownloadClick = { }
        )
    }
}