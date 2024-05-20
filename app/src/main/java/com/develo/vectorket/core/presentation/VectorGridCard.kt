package com.develo.vectorket.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.core.presentation.util.BundleIcon
import com.develo.vectorket.core.presentation.util.FORMAT_JPG
import com.develo.vectorket.core.presentation.util.StarIcon
import com.develo.vectorket.core.presentation.util.fileFormat
import com.develo.vectorket.ui.theme.VectorketTheme

@Composable
fun VectorGridCard(
    modifier: Modifier = Modifier,
    vector: Vector,
    size: Dp,
    onClick: () -> Unit
) {
    val format = vector.thumbnail.fileFormat

    Card(
        modifier = modifier,
        shape = RectangleShape,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(vector.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = "Vector",
                contentScale = if (format == FORMAT_JPG) ContentScale.Crop else ContentScale.Fit,
                modifier = if (format == FORMAT_JPG) Modifier.fillMaxSize() else Modifier.size(size)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.TopStart)
            ) {
                if (vector.premium) {
                    StarIcon(
                        Modifier
                            .size(16.dp)
                            .align(Alignment.CenterStart)
                    )
                }

                if (vector.bundled) {
                    BundleIcon(
                        Modifier
                            .size(12.dp)
                            .align(Alignment.CenterEnd),
                        0.5f
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun VectorGridCardPreview() {
    VectorketTheme {
        VectorGridCard(
            modifier = Modifier.size(120.dp, 150.dp),
            vector = Vector(
                title = "Cinderella",
                bundled = true,
                premium = true
            ),
            size = 64.dp,
            onClick = { }
        )
    }
}